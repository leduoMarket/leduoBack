package com.ledo.market.service;

import com.ledo.market.entity.StockIn;
import com.ledo.market.mapper.StockInMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王梦琼
 * 为StockIn接口提供服务
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
                    log.info("-从数据库中查询入库单数据");
                }
            }
        }else{
            log.info("-从缓存中查询入库单数据");
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
     * */
    public ResultUtil stockInAddfunc(StockIn stockInRecord){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.set("addstockInObj",stockInRecord);
        if(stockInMapper.insert(stockInRecord)==0){
            log.error("获取到入库单记录，但是插入失败");
            resultUtil.setCode(201);
            resultUtil.setMessage("入库单记录插入失败");
        };
        resultUtil.setCode(200);
        log.info("插入代码为"+stockInRecord.getInumber()+"的商品"+stockInRecord.getIcount()+"个");
        resultUtil.setMessage("插入入库单记录成功");
        return resultUtil;
    }
}
