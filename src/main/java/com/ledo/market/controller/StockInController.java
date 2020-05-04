package com.ledo.market.controller;

import com.ledo.market.entity.StockIn;
import com.ledo.market.mapper.StockInMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StockInController {
    @Resource
    StockInMapper stockinmapper;
    @CrossOrigin
    @GetMapping("/stock")

    public List<StockIn> selectAll(){

        return stockinmapper.selectAll();
    }
    public StockIn selectByPrimaryKey(String inumber){
        return stockinmapper.selectByPrimaryKey(inumber);
    }
}
