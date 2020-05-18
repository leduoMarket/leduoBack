package com.ledo.market.controller;

import com.ledo.market.entity.FlowPayments;
import com.ledo.market.service.FlowPaymentService;
import com.ledo.market.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/treasure")
public class FlowPaymentController {
    @Resource
    FlowPaymentService flowPaymentService;

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
    public ResultUtil delgoods(@RequestParam(value = "pnumber") Integer pnumber) {
        ResultUtil resultUtil = new ResultUtil();
        if (pnumber == null) {
            resultUtil.setCode(201);
            resultUtil.setMessage("被删除流水账号不能为空");
            return resultUtil;
        }
        return flowPaymentService.delRecord(pnumber);
    }
}
