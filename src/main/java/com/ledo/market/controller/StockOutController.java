package com.ledo.market.controller;
import com.ledo.market.entity.StockOut;
import com.ledo.market.mapper.StockOutMapper;
import com.ledo.market.service.StockOutService;
import com.ledo.market.utils.ResultUtil;
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
    @Resource
    StockOutService stockOutService;

    @GetMapping("/getStockOutList")
    public ResultUtil selectAll(){
        return stockOutService.getAllStockOutRecord();
    }

    /**
     * 增加某一条出库记录
     * */
    @PostMapping("/addStockOutRecord")
    @ResponseBody
    public ResultUtil addstockout(@RequestBody StockOut stockOutRecord){
        ResultUtil resultUtil = new ResultUtil();
        if(stockOutRecord==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("需要插入的出库记录不能为空");
            return resultUtil;
        }
        return stockOutService.addStockOutRecord(stockOutRecord);
    }

    /**
     * 删除出库单上的某一条出库记录
     * */
    @DeleteMapping("/delStockOutRecord")
    public ResultUtil delemp(@RequestParam(value = "stockOutId") String onumber) {
        ResultUtil resultUtil = new ResultUtil();
        if (onumber==null) {
            resultUtil.setCode(201);
            resultUtil.setMessage("需要删除的出库记录单号不能为空");
            return resultUtil;
        }
        return stockOutService.delStockOutRecord(onumber);
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
