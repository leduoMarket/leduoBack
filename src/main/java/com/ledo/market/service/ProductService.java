package com.ledo.market.service;

import com.ledo.market.entity.Product;
import com.ledo.market.mapper.ProductMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 * 为商品的信息显示提供界面
 */
@Slf4j
@Service
public class ProductService {
    @Resource
    RedisUtil redisUtil;
    @Resource
    ProductMapper productMapper;
    /**
     * 获取到所有的商品信息
     * */
    public ResultUtil getAllProductInfo(){
        ResultUtil resultUtil = new ResultUtil();
        List productList = (List) redisUtil.get("productList");
        if(productList==null){
            productList = (List) redisUtil.get("productList");
            if(productList==null){
                synchronized (this){
                    productList = productMapper.selectAll();
                    redisUtil.set("productList",productList);
                }
            }
        }
       if(productList==null){
           resultUtil.setCode(201);
           resultUtil.setMessage("没有查询到商品表的信息");
           return resultUtil;
       }
       resultUtil.setCode(200);
       resultUtil.setData(productList);
       return resultUtil;
    }
    /**
     * 增加商品信息,采用延时双删保持和入库单的信息一致
     * */
    public ResultUtil addProductRecord(Product productRecord){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("productList");
        try {
            productMapper.insert(productRecord);
        }catch(DuplicateKeyException e){
            resultUtil.setCode(201);
            resultUtil.setMessage("商品号为"+productRecord.getGid()+"的商品已经存在");
            log.warn("-商品号为:"+productRecord.getGid()+"的商品已经存在");
            return resultUtil;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("增加商品信息延时异常");
        }
        redisUtil.del("productList");
        resultUtil.setCode(200);
        log.info("-成功插入商品号为:"+productRecord.getGid()+"的记录");
        resultUtil.setMessage("成功插入一条记录");
        return resultUtil;
    }
    /*
     * 删除商品信息
     * */
    public ResultUtil delProductRecord(Long gid){
        redisUtil.del("productList");
        ResultUtil resultUtil = new ResultUtil();
        productMapper.delete(gid);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("删除商品信息延时异常");
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("删除成功");
        log.warn("-删除商品代码为"+gid+"d的商品");
        return resultUtil;
    }
}
