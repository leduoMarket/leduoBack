package com.ledo.market.controller;

import com.ledo.market.entity.ProductPricing;
import com.ledo.market.mapper.ProductPricingMapper;
import com.ledo.market.service.ProductPricingService;
import com.ledo.market.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class ProductPricingController {
    @Resource
    ProductPricingService productPricingService;
    @Resource
    ProductPricingMapper productPricingmapper;

    /**
     * 获取所有的商品定价信息
     * */
    @GetMapping("/getProductPricingInfo")
    public ResultUtil selectAll(){
        return productPricingService.getAllProductPricingInfo();
    }

    /**
     * 增加商品定价信息
     * */
    @PostMapping("/addProductPricingInfo")
    @ResponseBody
    public ResultUtil addpprice(@RequestBody ProductPricing productPricingInfoRecord){
        ResultUtil resultUtil = new ResultUtil();
       if(productPricingInfoRecord==null){
           resultUtil.setCode(201);
           resultUtil.setMessage("需要增加的商品定价信息不能为空");
           return resultUtil;
       }
       return productPricingService.addProductPricingRecord(productPricingInfoRecord);
    }
    /**
     * 删除商品定价信息
     * */
    @DeleteMapping("/delProductPricingInfo")
    public ResultUtil delemp(@RequestParam(value = "priceId") Long gid) {
        ResultUtil resultUtil = new ResultUtil();
        if(gid==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("被删除的商品号不能为空");
            return resultUtil;
        }
        return productPricingService.delProductPricingInfo(gid);
    }
}
