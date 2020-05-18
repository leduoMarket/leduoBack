package com.ledo.market.service;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.ledo.market.entity.ProductReturn;
import com.ledo.market.mapper.ProductReturnMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ProductReturnService {
    @Resource
    RedisUtil redisUtil;
    @Resource
    ProductReturnMapper productReturnMapper;

    /**
     * 查询所有的退货单记录
     * */
    public ResultUtil getAll(){
        ResultUtil resultUtil = new ResultUtil();
        List<ProductReturn> productReturnList = (List<ProductReturn>) redisUtil.get("productReturnList");
        if(productReturnList==null){
            productReturnList = (List<ProductReturn>) redisUtil.get("productReturnList");
            if(productReturnList==null){
                productReturnList=productReturnMapper.selectAll();
                redisUtil.set("productReturnList",productReturnList);
            }
        }
        if(productReturnList==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("没有查询到退货单的记录");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setData(productReturnList);
        return resultUtil;
    }
    /**
     * 删除退货单记录
     * */
    public ResultUtil delRecord(Long gid){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("productReturnList");
        productReturnMapper.delete(gid);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
           System.out.println("删除退货单记录时的延时错误");
        }
        redisUtil.del("productReturnList");
        resultUtil.setCode(200);
        resultUtil.setMessage("删除记录成功");
        return resultUtil;
    }
    /**
     * 新增退货单记录
     * */
    public ResultUtil addRecord(ProductReturn productReturnREcord){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("productReturnList");
        try{
            productReturnMapper.insert(productReturnREcord);
        }catch(DuplicateKeyException e){
            resultUtil.setCode(201);
            resultUtil.setMessage("已经存在商品号为"+productReturnREcord.getGid()+"退货单记录");
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("增加退货单记录时的延时错误");
        }
        redisUtil.del("productReturnList");
       resultUtil.setCode(200);
       resultUtil.setMessage("插入成功");
       return resultUtil;
    }
}
