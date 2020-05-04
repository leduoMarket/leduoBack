package com.ledo.market.controller;

import com.ledo.market.entity.ProductPricing;
import com.ledo.market.mapper.ProductPricingMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodsPricingCtroller {
    @Resource
    ProductPricingMapper productPricingmapper;
    @CrossOrigin
    @GetMapping("/productprice")
    public List<ProductPricing> selectAll(){
        return productPricingmapper.selectAll();
    }


}
