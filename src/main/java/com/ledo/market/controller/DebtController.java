package com.ledo.market.controller;

import com.ledo.market.service.DebtService;
import com.ledo.market.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 * 查询关于欠款单相关的信息，提供接口
 */
@RestController
@RequestMapping("/treasure")
public class DebtController {
    @Resource
    DebtService debtService = new DebtService();
    @GetMapping("/debt")
    public ResultUtil selectAll(){
        return debtService.getAll();
    }

    @DeleteMapping("/delDebt")
    public ResultUtil deldebt(@RequestParam(value = "debtId") String debtId) {
        ResultUtil resultUtil = new ResultUtil();
        if(debtId==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("被删除的欠款单号不能为空");
            return resultUtil;
        }
        return debtService.delDebtRecord(debtId);
    }
}
