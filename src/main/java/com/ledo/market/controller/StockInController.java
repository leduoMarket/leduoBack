package com.ledo.market.controller;

import com.ledo.market.entity.StockIn;
import com.ledo.market.mapper.StockInMapper;
import com.ledo.market.result.StatusCodeResult;
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
<<<<<<< HEAD
    @CrossOrigin
    @PostMapping("/addstock")
    @ResponseBody
    public StatusCodeResult addstock(@RequestBody StockIn reqstock){
        System.out.println(reqstock.getGid());
        System.out.println(reqstock.getIdate());
        System.out.println(reqstock.getIcount());
        System.out.println(stockinmapper.insert(reqstock));
        return new StatusCodeResult(200);
    }
=======


>>>>>>> b80ddc63fab58925414cb117d447f8412ab01833
}
