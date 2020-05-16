package com.ledo.market.controller;

import com.ledo.market.entity.StockIn;
import com.ledo.market.entity.StockOut;
import com.ledo.market.mapper.StockOutMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/staff")
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
    @GetMapping("/querystockOut")
    public StockOut selectByPrimaryKey(@RequestParam(value = "onumber") String inumber){
        StockOut s = stockoutmapper.selectByPrimaryKey(inumber);
        if(s!=null){
            System.out.println("stockOutItem"+s.getOnumber());
            return s;
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/addstockOut")
    @ResponseBody
    public StatusCodeResult addstockout(@RequestBody StockOut reqstockout){
        System.out.print(reqstockout.getGid());
        System.out.println(stockoutmapper.insert(reqstockout));
        return new StatusCodeResult(200);
    }

    @CrossOrigin
    @DeleteMapping("/delstockOut")
    public StatusCodeResult delemp(@RequestParam(value = "stockOutId") String stockOutId) {
        System.out.println("empID:" + stockOutId);
        System.out.println(stockoutmapper.delete(stockOutId));
        return new StatusCodeResult(200);
    }

    @CrossOrigin
    @GetMapping("/analyseoutdate")
    public List<Date> putstockoutdate(){
        List<Date> outdate = stockoutmapper.putstockoutdate();
        return outdate;
    }

    //给分析图表传入数据
    @CrossOrigin
    @GetMapping("/analyseoutsum")
    public List<Map> putstockoutsum(){
        return stockoutmapper.putstockoutsum();

    }
}
