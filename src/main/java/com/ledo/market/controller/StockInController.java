package com.ledo.market.controller;
import com.ledo.market.entity.StockIn;
import com.ledo.market.mapper.StockInMapper;
import com.ledo.market.result.StatusCodeResult;
import com.ledo.market.service.StockInService;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

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
    @GetMapping("stockInList")
    public ResultUtil getAllRecord(){
        log.info("-调用stockInService方法");
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

    @CrossOrigin
    @DeleteMapping("delstockIn")
    public StatusCodeResult delemp(@RequestParam(value = "stockInId") String stockInId) {
        System.out.println("empID:" + stockInId);
        System.out.println(stockinmapper.delete(stockInId));
        return new StatusCodeResult(200);
    }

}
