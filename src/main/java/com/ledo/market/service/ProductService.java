package com.ledo.market.service;

import com.ledo.market.mapper.ProductMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
       return null;
    }
    /**
     * 增加商品信息
     * */
    public ResultUtil addProductRecord(){
        return null;
    }
    /**
     * 删除商品信息
     * */
    public ResultUtil delProductRecord(){
        return null;
    }
}
