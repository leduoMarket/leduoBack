package com.ledo.market.controller;
import com.ledo.market.entity.StockIn;
import com.ledo.market.mapper.StockInMapper;
import com.ledo.market.service.StockInService;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 王梦琼
 * 实现入库单的各个接口功能
 */
@Slf4j
@RestController
@RequestMapping("/staff")
public class StockInController {
    @Resource
    StockInService stockInService;
    @Resource
    StockInMapper stockinmapper;
    //使用了redis缓存
    @GetMapping("stockInList")
    public ResultUtil getAllRecord(){
       return  stockInService.getAll();
    }
    /**
     * 增加入库单的接口
     * */
    @PostMapping("stockInAdd")
    @ResponseBody
    public ResultUtil addstock(@RequestBody StockIn addStockInRecord){
       ResultUtil resultUtil = new ResultUtil();
        if(addStockInRecord!=null){
            resultUtil = stockInService.stockInAddfunc(addStockInRecord);
        }else{
            log.error("-没有获取到需要插入的入库单记录，插入失败");
            resultUtil.setCode(201);
            resultUtil.setMessage("没有获取到需要插入的对象");
        }
        return resultUtil;
    }

    @DeleteMapping("delstockIn")
    public ResultUtil delemp(@RequestParam(value = "inumber") String inumber) {
        ResultUtil resultUtil = new ResultUtil();
        if(inumber==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("要删除的入库单号为空");
            return resultUtil;
        }
        return stockInService.delStockInRecord(inumber);
    }



}
