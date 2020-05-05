package com.ledo.market.controller;

import com.ledo.market.entity.StockIn;
import com.ledo.market.mapper.StockInMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @GetMapping("/queiryStockIn")
    public StockIn selectByPrimaryKey(@RequestParam(value = "gid") Integer gid){
        StockIn s = stockinmapper.selectByInumber(gid);
        return s;
    }


}
