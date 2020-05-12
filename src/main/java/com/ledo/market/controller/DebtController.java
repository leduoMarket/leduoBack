package com.ledo.market.controller;

import com.ledo.market.entity.Debt;
import com.ledo.market.mapper.DebtMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/treasure")
public class DebtController {
    @Resource
    DebtMapper debtMapper;
    @CrossOrigin
    @GetMapping("/debt")
    public List<Debt> selectAll(){
        return debtMapper.selectAll();
    }

    @CrossOrigin
    @GetMapping("/queryDebt")
    public Debt selectByPrimaryKey(@RequestParam(value="dnumber") String dnumber){
        Debt s = debtMapper.selectByPrimaryKey(dnumber);
        if(s!=null){
            System.out.println("returnItem"+s.getDnumber());
            return s;
        }
        return null;
    }
    @CrossOrigin
    @DeleteMapping("/delDebt")
    public StatusCodeResult deldebt(@RequestParam(value = "debtId") String debtId) {
        System.out.println("empID:" + debtId);
        System.out.println(debtMapper.delete(debtId));
        return new StatusCodeResult(200);
    }
}
