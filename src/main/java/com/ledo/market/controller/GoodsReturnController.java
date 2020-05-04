package com.ledo.market.controller;

import com.ledo.market.entity.ProductReturn;
import com.ledo.market.mapper.ProductReturnMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
