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
        log.info("- 调用stockInService方法");
       return  stockInService.getAll();
    }
//    @GetMapping("queryStockIn")
//    public StockIn selectByPrimaryKey(@RequestParam(value = "inumber") String inumber){
//        StockIn s = stockinmapper.selectByInumber(inumber);
//        if(s!=null){
//            System.out.println("stockInItem"+s.getInumber());
//            return s;
//        }
//        return null;
//    }

    @PostMapping("stockInAdd")
    @ResponseBody
    public StatusCodeResult addstock(@RequestBody StockIn addStockIn){
       ResultUtil resultUtil = new ResultUtil();
        if(addStockIn!=null){
//            statusCodeResult = stockInService.stockInAddf(addStockIn);
        }else{
            resultUtil.setCode(201);
           resultUtil.setMessage("没有获取到需要插入的对象");
           resultUtil.setData("");
        }

//        System.out.println(reqstock.getGid());
//        System.out.println(reqstock.getIdate());
//        System.out.println(reqstock.getIcount());
//        System.out.println(stockinmapper.insert(reqstock));
        return new StatusCodeResult(200);
    }
    @CrossOrigin
    @DeleteMapping("delstockIn")
    public StatusCodeResult delemp(@RequestParam(value = "stockInId") String stockInId) {
        System.out.println("empID:" + stockInId);
        System.out.println(stockinmapper.delete(stockInId));
        return new StatusCodeResult(200);
    }

}
