package com.ledo.market.service;
import com.ledo.market.entity.StockIn;
import com.ledo.market.mapper.StockInMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author 王梦琼
 * 为StockIn接口提供服务,支持入库单的整体查询功能和新建删除功能
 * redis缓存采用延时双删保持缓存与数据库数据的一致性
 */
@Service
@Slf4j
public class StockInService {
    @Resource
    ResultUtil resultUtil;
    @Resource
    StockInMapper stockInMapper;
    @Resource
    RedisUtil redisUtil;

    public ResultUtil getAll(){
        List<StockIn> list = (List<StockIn>) redisUtil.get("stockInList");
        if(list == null){
            synchronized (this){
                list = (List<StockIn>) redisUtil.get("stockInList");
                if(list==null){
                    list = stockInMapper.selectAll();
                    redisUtil.set("stockInList",list);
                    log.info("-从数据库中查询所有的入库单数据");
                }
            }
        }else{
            log.info("-从缓存中查询所有的入库单数据");
        }
        if(list==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("数据库还没有关于入库单的相关信息");
            resultUtil.setData("");
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("查询到入库单所有数据");
        resultUtil.setData(list);
        return  resultUtil;
    }
    /**
     * 增加入库单的服务类,
     * 增加的时候先存入缓存，再存入数据库
     * 采用延时双删策略进行redis和数据库的数据一致性
     * */
    public ResultUtil stockInAddfunc(StockIn stockInRecord){
        int influenceLine = 0;
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("stockInList");
            try {
                influenceLine = stockInMapper.insert(stockInRecord);
            } catch (SQLException e) {
                log.error("-插入入库单是的供应商不在供应商表中");
                resultUtil.setCode(201);
                resultUtil.setMessage("输入的供应商在入库单不存在,请先填写供应商表");
                return resultUtil;

            }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("-redis缓存延时错误");
        }
        if(influenceLine==0){
            log.error("-获取到入库单记录，但是插入失败");
            resultUtil.setCode(201);
            resultUtil.setMessage("入库单记录插入失败");
            return resultUtil;
        };
        //再次删除，删除在写入时候读取造成的脏数据
        redisUtil.del("stockInList");
        resultUtil.setCode(200);
        log.info("插入代码为"+stockInRecord.getInumber()+"的商品"+stockInRecord.getIcount()+"个");
        resultUtil.setMessage("插入入库单记录成功");
        log.info("-入库单号为"+stockInRecord.getInumber()+"的记录插入成功");
        return resultUtil;
    }
    public ResultUtil delStockInRecord(String inumber){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("stockInList");
        stockInMapper.delete(inumber);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("-redis缓存延时错误");
        }
        //采用延时双删策略确保被删除的数据一致
        redisUtil.del("stockInList");
        resultUtil.setCode(200);
        resultUtil.setMessage("删除入库单记录成功");
        log.info("-入库单号为"+inumber+"的记录成功删除");
        return resultUtil;
    }
}
