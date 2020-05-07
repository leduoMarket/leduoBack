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
    @GetMapping("/stockIn")

    public List<StockIn> selectAll(){

        return stockinmapper.selectAll();
    }

    @CrossOrigin
    @GetMapping("/queryStockIn")
    public StockIn selectByPrimaryKey(@RequestParam(value = "inumber") String inumber){
        StockIn s = stockinmapper.selectByInumber(inumber);
        if(s!=null){
            System.out.println("stockInItem"+s.getInumber());
            return s;
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/addstockIn")
    @ResponseBody
    public StatusCodeResult addstock(@RequestBody StockIn reqstock){
        System.out.println(reqstock.getGid());
        System.out.println(reqstock.getIdate());
        System.out.println(reqstock.getIcount());
        System.out.println(stockinmapper.insert(reqstock));
        return new StatusCodeResult(200);
    }

}
