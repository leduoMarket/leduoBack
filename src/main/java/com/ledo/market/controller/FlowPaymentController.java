package com.ledo.market.controller;

import com.ledo.market.entity.FlowPayments;
import com.ledo.market.mapper.FlowPaymentsMapper;
import com.ledo.market.result.StatusCodeResult;
import org.hibernate.validator.internal.util.privilegedactions.GetMethodFromPropertyName;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/treasure")
public class FlowPaymentController {
    @Resource
    FlowPaymentsMapper flowPaymentsMapper;
    @GetMapping("/payments")
    public List<FlowPayments> selectAll(){
        return flowPaymentsMapper.selectAll();
    }

    @GetMapping("/queryPaymentOfFlowCount")
    public FlowPayments selectByPrimaryKey(@RequestParam(value="pnumber") Integer pnumber){
        FlowPayments s = flowPaymentsMapper.selectByPrimaryKey(pnumber);
        if(s!=null){
            System.out.println("returnItem"+s.getPnumber());
            return s;
        }
        return null;
    }

    @PostMapping("/addpayment")
    @ResponseBody
    public StatusCodeResult addgoods(@RequestBody FlowPayments reqgood){
        System.out.print(reqgood.getPnumber());
        System.out.print(reqgood.getPcategory());
        System.out.print(reqgood.getPdate()+"!");
        System.out.print(reqgood.getPremainningAmount());
        System.out.print(reqgood.getPsourceShop());
        System.out.println(flowPaymentsMapper.insert(reqgood));
        return new StatusCodeResult(200);
    }

    @DeleteMapping("/delpayment")
    public StatusCodeResult delgoods(@RequestParam(value = "stockInId") String stockInId) {
        System.out.println("empID:" + stockInId);
        Integer goodsIds;
        goodsIds = Integer.parseInt(stockInId);
        System.out.println(flowPaymentsMapper.delete(goodsIds));
        return new StatusCodeResult(200);
    }
}
