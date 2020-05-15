package com.ledo.market.service;

import com.ledo.market.entity.Log;
import com.ledo.market.mapper.LogMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王梦琼
 * 对logController提供查询服务
 */

@Service
@Slf4j
public class LogService {
    @Resource
    LogMapper logMapper;
    @Resource
    ResultUtil resultUtil;
    @Resource
    RedisUtil redisUtil;
    /**
    * 查询数据库中前200条数据在前端进行显示分析
     * 查询日志的同时不产生日志信息
    * */
    public ResultUtil getSomeLog(){
        List logs = (List) redisUtil.get("someLogs");
        if(logs==null){
            logs = (List) redisUtil.get("someLogs");
            if(logs==null){
                synchronized (this){
                    logs = logMapper.selectSome();
                    redisUtil.set("someLogs",logs);
                }
            }
        }
        if(logs==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("暂时没有查询到任何日志记录");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("成功查询到日志信息");
        resultUtil.setData(logs);
        return resultUtil;
    }
    /**
     * 查询出剩下的Logs信息
     * */

    public ResultUtil getAllLogs(){
        List allLogs = (List) redisUtil.get("allLogs");
        if(allLogs==null){
            allLogs = (List) redisUtil.get("allLogs");
            if(allLogs==null){
                allLogs = logMapper.selectAll();
                redisUtil.set("allLogs",allLogs);
            }
        }
        if(allLogs==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("暂时没有查询到任何日志记录");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("成功查询到日志信息");
        resultUtil.setData(allLogs);
        return resultUtil;
    }
}
