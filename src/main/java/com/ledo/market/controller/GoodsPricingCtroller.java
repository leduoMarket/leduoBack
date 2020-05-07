package com.ledo.market.controller;

import com.ledo.market.entity.ProductPricing;
import com.ledo.market.mapper.ProductPricingMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodsPricingCtroller {
    @Resource
    ProductPricingMapper productPricingmapper;
    @CrossOrigin
    @GetMapping("/commodityPricing")
    public List<ProductPricing> selectAll(){
        return productPricingmapper.selectAll();
    }
    @CrossOrigin
    @PostMapping("/addcommodityPricing")
    @ResponseBody
    public StatusCodeResult addpprice(@RequestBody ProductPricing reqpprice){
        System.out.println(reqpprice.getGid());
        System.out.println(reqpprice.getGname());
        System.out.println(productPricingmapper.insert(reqpprice));
        return new StatusCodeResult(100);
    }




}
