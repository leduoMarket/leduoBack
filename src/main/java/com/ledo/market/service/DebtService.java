package com.ledo.market.service;

import com.ledo.market.entity.Debt;
import com.ledo.market.mapper.DebtMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DebtService {
    @Resource
    RedisUtil redisUtil;
    @Resource
    DebtMapper debtMapper;
    /**
     *
     * 查询所有关于欠款单相关的信息
     * */
    public ResultUtil getAll(){
        ResultUtil resultUtil = new ResultUtil();
        List<Debt> debtList = (List<Debt>) redisUtil.get("debtList");
        if(debtList==null){
            debtList = (List<Debt>) redisUtil.get("debtList");
            if(debtList==null){
                synchronized (this){
                    debtList=debtMapper.selectAll();
                    redisUtil.set("debtList",debtList);
                }
            }
        }
        if(debtList==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("没有查到欠款单相关的记录");
            return resultUtil;
        }
       resultUtil.setCode(200);
        resultUtil.setData(debtList);
        return resultUtil;
    }


    /**
     * 增加欠款单相关的,功能有待考虑
     * */
//    public ResultUtil addDebtRecord(Debt debtRecord){
//        redisUtil.del("debtList");
//        ResultUtil resultUtil = new ResultUtil();
//        try{
//            debtMapper.insert(debtRecord);
//        }catch (DuplicateKeyException e){
//            resultUtil.setCode(201);
//            resultUtil.setMessage("已经存在了欠款单号为："+debtRecord.getDnumber()+"的记录");
//            log.error("-重复插入欠款单号为："+debtRecord.getDnumber()+"的记录");
//            return resultUtil;
//        }
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            System.out.println("增加欠款单记录延时异常");
//        }
//        redisUtil.del("debtList");
//        resultUtil.setCode(200);
//        resultUtil.setMessage("插入记录成功");
//        log.info("-插入欠款单号为"+debtRecord.getDnumber()+"成功");
//        return resultUtil;
//    }


    /**
     * 删除欠款单
     * */
    public ResultUtil delDebtRecord(String dnumber){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("debtList");
        debtMapper.delete(dnumber);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("删除欠款单记录延时异常");
        }
        redisUtil.del("debtList");
        resultUtil.setCode(200);
        resultUtil.setMessage("删除成功");
        log.info("-删除欠款单号为："+dnumber+"的记录成功");
        return resultUtil;
    }
}
