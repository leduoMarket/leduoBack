package com.ledo.market.service;

import com.ledo.market.entity.Vender;
import com.ledo.market.mapper.VenderMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class VenderService {
    @Resource
    RedisUtil redisUtil;
    @Resource
    VenderMapper venderMapper;
    /**
     * 查询所有的供应商记录
     * */
    public ResultUtil getAllVenders(){
        ResultUtil resultUtil = new ResultUtil();
        List<Vender> venderList = (List) redisUtil.get("venderList");
        if(venderList==null){
            venderList = (List) redisUtil.get("venderList");
            synchronized (this){
                venderList = venderMapper.selectAll();
                redisUtil.set("venderList",venderList);
            }
        }
        log.info("-查看供应商表信息");
        if(venderList==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("暂时没有供应商表的记录信息");
           return resultUtil;
        }
        //查看成功的时候只是查看，不需要查看所有的消息
        resultUtil.setCode(200);
        resultUtil.setData(venderList);
        return resultUtil;
    }

    /**
     * 新增一个供应商记录,采用延时双删保持与数据库的信息的一致性
     * */
    public ResultUtil addVenderRecord(Vender vender){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("venderList");
        int influenceLine = 0;
        try{
            influenceLine = venderMapper.insert(vender);
        }catch(DuplicateKeyException e){
            resultUtil.setCode(201);
            resultUtil.setMessage("已经存在代码为："+vender.getVid()+"的供应商");
            return resultUtil;
        }

        if(influenceLine==0){
            resultUtil.setCode(201);
            resultUtil.setMessage("插入供应商表信息失败");
            log.warn("-插入："+vender.getVname()+"供应商失败");
            return resultUtil;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
           System.out.println("-供应商信息增加时缓存延时错误");
        }
        redisUtil.del("venderList");
        resultUtil.setCode(200);
        resultUtil.setMessage("成功插入供应商信息");
        log.info("-插入："+vender.getVid()+" "+vender.getVname()+"供应商成功");
        return resultUtil;
    }

    /**
     * 删除一个供应商记录，删除供应商时候存在外码约束问题，是否把原来所有的都删除还是
     * 不允许删除？
     * */
    public ResultUtil delVenderRecord(Integer vid){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("venderList");
        venderMapper.delete(vid);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("-供应商信息删除时缓存延时错误");
        }
        redisUtil.del("venderList");
        resultUtil.setCode(200);
        resultUtil.setMessage("删除供应商信息成功");
        log.info("-删除："+vid+"供应商成功");
        return resultUtil;
    }
}
