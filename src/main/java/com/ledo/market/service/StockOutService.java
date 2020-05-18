package com.ledo.market.service;

import com.ledo.market.entity.Log;
import com.ledo.market.entity.StockOut;
import com.ledo.market.mapper.StockOutMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王梦琼
 * 为stockOut接口提供增删查的服务
 */
@Service
@Slf4j
public class StockOutService {
    @Resource
    StockOutMapper stockOutMapper;
    @Resource
    RedisUtil redisUtil;
    public ResultUtil getAllStockOutRecord(){
        ResultUtil resultUtil = new ResultUtil();
        List<StockOut> stockOutlist = (List<StockOut>) redisUtil.get("stockOutList");
        if(stockOutlist==null){
            stockOutlist = (List<StockOut>) redisUtil.get("stockOutList");
            if(stockOutlist==null){
                synchronized (this){
                    stockOutlist = stockOutMapper.selectAll();
                    redisUtil.set("stockOutList",stockOutlist);
                    log.info("-从数据库中读取出库单数据");
                }
            }else{
                log.info("-从缓存中读取出库单数据");
            }
        }else{
            log.info("-从缓存中读取出库单数据");
        }
        if(stockOutlist==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("没有读取到任何出库单数据");
            return resultUtil;
        }
        resultUtil.setCode(200);
        return resultUtil;
    }
    /**
     * 向出库单中增加记录，采用延时双删保持缓存数据与数据库数据一致
     * */
    public ResultUtil addStockOutRecord(StockOut stockOutRecord){
        int influenceLine = 0;
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("stockOutList");
        influenceLine = stockOutMapper.insert(stockOutRecord);
        if(influenceLine==0){
            resultUtil.setCode(201);
            resultUtil.setMessage("增加记录失败");
            log.error("-出库单号为"+stockOutRecord.getOnumber()+"的记录插入失败");
            return resultUtil;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("-缓存延时错误");
        }
        redisUtil.del("stockOutList");
        resultUtil.setCode(200);
        resultUtil.setMessage("插入成功");
        log.error("-出库单号为"+stockOutRecord.getOnumber()+"的记录插入成功");
        return resultUtil;
    }
    /**
     * 根据出库单号删除出库记录
     * */
    public ResultUtil delStockOutRecord(String onumber){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("stockOutList");
        stockOutMapper.delete(onumber);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("-缓存延时失败");
        }
        redisUtil.del("stockOutList");
        resultUtil.setCode(200);
        resultUtil.setMessage("删除出库记录成功");
        log.info("-删除单号为："+onumber+"的出库记录成功");
        return resultUtil;
    };
}
