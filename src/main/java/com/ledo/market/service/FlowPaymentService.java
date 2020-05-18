package com.ledo.market.service;

import com.ledo.market.entity.FlowPayments;
import com.ledo.market.mapper.FlowPaymentsMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class FlowPaymentService {
    @Resource
    FlowPaymentsMapper flowPaymentsMapper;
    @Resource
    RedisUtil redisUtil;

    /**
     * 查询所有支付流水账相关的记录
     */
    public ResultUtil getAllRecord() {
        ResultUtil resultUtil = new ResultUtil();
        List<FlowPayments> paymentList = (List<FlowPayments>) redisUtil.get("flowPaymentList");
        if (paymentList == null) {
            paymentList = (List<FlowPayments>) redisUtil.get("flowPaymentList");
            if (paymentList == null) {
                synchronized (this) {
                    paymentList = flowPaymentsMapper.selectAll();
                    redisUtil.set("flowPaymentList", paymentList);
                }
            }
        }
        if (paymentList == null) {
            resultUtil.setCode(201);
            resultUtil.setMessage("没有查询的支付流水账相关的信息");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setData(paymentList);
        return resultUtil;
    }
    /**
     *
     * 新增支付流水账
     * */
    public ResultUtil addRecord(FlowPayments flowPayments){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("flowPaymentList");
        try{
            flowPaymentsMapper.insert(flowPayments);
        }catch(DuplicateKeyException e){
            resultUtil.setCode(201);
            resultUtil.setMessage("已经存在支付流水账为"+flowPayments.getPnumber()+"的记录");
            return resultUtil;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("新增支付流水账记录时延时异常");
        }
        redisUtil.del("flowPaymentList");
       resultUtil.setCode(200);
       resultUtil.setMessage("增加成功");
       return resultUtil;
    }

    /**
     * 删除支付流水账记录
     */
    public ResultUtil delRecord(Integer pnumber) {
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("flowPaymentList");
        flowPaymentsMapper.delete(pnumber);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("删除支付流水账记录时延时异常");
        }
        redisUtil.del("flowPaymentList");
        resultUtil.setCode(200);
        resultUtil.setMessage("删除成功");
        return resultUtil;
    }
}
