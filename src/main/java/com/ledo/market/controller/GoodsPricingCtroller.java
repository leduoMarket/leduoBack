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
    @GetMapping("/home/commodityPricing")
    public List<ProductPricing> selectAll(){
        return productPricingmapper.selectAll();
    }
    @CrossOrigin
    @PostMapping("/home/addcommodityPricing")
    @ResponseBody
    public StatusCodeResult addpprice(@RequestBody ProductPricing reqpprice){
        System.out.println(reqpprice.getGid());
        System.out.println(reqpprice.getGname());
        System.out.println(productPricingmapper.insert(reqpprice));
        return new StatusCodeResult(100);
    }
    @CrossOrigin
    @GetMapping("/home/querycommodityPricing")
    public ProductPricing selectByPrimaryKey(@RequestParam(value = "gid") Long gid){
        ProductPricing s = productPricingmapper.selectByPrimaryKey(gid);
        if(s!=null){
            System.out.println("priceItem"+s.getGid());
            return s;
        }
        return null;
    }
    @CrossOrigin
    @DeleteMapping("/home/delcommodityPricing")
    public StatusCodeResult delemp(@RequestParam(value = "priceId") String priceId) {
        System.out.println("empID:" + priceId);
        Long goodsIds;
        goodsIds = Long.parseLong(priceId);
        System.out.println(productPricingmapper.delete(goodsIds));
        return new StatusCodeResult(200);
    }
}
