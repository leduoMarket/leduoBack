package com.ledo.market.controller;

import com.ledo.market.entity.StockOut;
import com.ledo.market.mapper.StockOutMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StockOutController {
    @Resource
    StockOutMapper stockoutmapper;
    @CrossOrigin
    @GetMapping("/stockOut")

    public List<StockOut> selectAll(){
        return stockoutmapper.selectAll();
    }
    /*后面还可以写别的方法*/
    @CrossOrigin
    @PostMapping("/addstockOut")
    @ResponseBody
    public StatusCodeResult addstockout(@RequestBody StockOut reqstockout){
        System.out.print(reqstockout.getGid());
        System.out.println(stockoutmapper.insert(reqstockout));
        return new StatusCodeResult(200);
    }
}
