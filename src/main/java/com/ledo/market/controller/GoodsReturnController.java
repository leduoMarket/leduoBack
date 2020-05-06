package com.ledo.market.controller;

import com.ledo.market.entity.ProductPricing;
import com.ledo.market.entity.ProductReturn;
import com.ledo.market.mapper.ProductReturnMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodsReturnController {
    @Resource
    ProductReturnMapper productReturnMapper;
    @CrossOrigin
    @GetMapping("/productreturn")
    public List<ProductReturn> selectAll(){
        return productReturnMapper.selectAll();
    }

    @CrossOrigin
    @PostMapping("/addproductreturn")
    @ResponseBody
    public StatusCodeResult addpreturn(@RequestBody ProductReturn reqreturn){
        System.out.println(reqreturn.getGid());
        System.out.println(productReturnMapper.insert(reqreturn));
        return new StatusCodeResult(200);
    }
}
