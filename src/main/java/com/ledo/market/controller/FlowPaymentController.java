package com.ledo.market.controller;

import com.ledo.market.entity.FlowPayments;
import com.ledo.market.mapper.StockInMapper;
import com.ledo.market.mapper.StockOutMapper;
import com.ledo.market.service.FlowPaymentService;
import com.ledo.market.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/treasure")
public class FlowPaymentController {
    @Resource
    StockOutMapper stockOutMapper;

    @Resource
    FlowPaymentService flowPaymentService;

    @Resource
    StockInMapper stockInMapper;
    @GetMapping("/paymentsList")
    public ResultUtil selectAll() {
        return flowPaymentService.getAllRecord();
    }

    @PostMapping("/addPayment")
    @ResponseBody
    public ResultUtil addPayments(@RequestBody FlowPayments flowPaymentsRecord) {
        ResultUtil resultUtil = new ResultUtil();
        if (flowPaymentsRecord == null) {
            resultUtil.setCode(201);
            resultUtil.setMessage("需要插入的流水账记录不能为空");
            return resultUtil;
        }
        return flowPaymentService.addRecord(flowPaymentsRecord);
    }

    @DeleteMapping("/delPayment")
    public ResultUtil delgoods(@RequestParam(value = "pnumber") String pnumber) {
        ResultUtil resultUtil = new ResultUtil();
        if (pnumber == null) {
            resultUtil.setCode(201);
            resultUtil.setMessage("被删除流水账号不能为空");
            return resultUtil;
        }
        return flowPaymentService.delRecord(pnumber);
    }

    //给分析数据图表传入数据
    @GetMapping("analyseinsum")
    public ResultUtil putstockinsum(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setMessage("成功");
        resultUtil.setData(stockInMapper.putstockinsum());
        return resultUtil;
    }

    //给分析图表传入数据
    @GetMapping("analyseoutsum")
    public ResultUtil putstockoutsum(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setMessage("成功");
        resultUtil.setData(stockOutMapper.putstockoutsum());
        return resultUtil;
    }
}
