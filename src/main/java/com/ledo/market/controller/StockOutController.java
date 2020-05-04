package com.ledo.market.controller;

import com.ledo.market.entity.StockOut;
import com.ledo.market.mapper.StockOutMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StockOutController {
    @Resource
    StockOutMapper stockoutmapper;
    @CrossOrigin
    @GetMapping("/stockout")

    public List<StockOut> selectAll(){
        return stockoutmapper.selectAll();
    }
    /*后面还可以写别的方法*/
}
