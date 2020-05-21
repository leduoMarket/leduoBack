package com.ledo.market.service;

import com.ledo.market.entity.Log;
import com.ledo.market.mapper.LogMapper;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.utils.GetRoleUtil;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    RedisUtil redisUtil;
    @Resource
    UserMapper userMapper;

    /**
    * 查询数据库中前200条数据在前端进行显示分析
     * 查询日志的同时不产生日志信息
     * 根据当前用户角色的不同，返回不同的日志记录,日志信息的缓存时间是多少？
    * */
    //获取当前登录用户的角色
    public Map<String,String> getRole(){
        final int ADMIN = 3;
        final int TREASURE = 2;
        final int STAFF = 1;
        Subject currentUser = SecurityUtils.getSubject();
        Object principal = currentUser.getPrincipal();
        String uid = principal.toString();
        Set<String> roles = userMapper.getRolesByuid(uid);
        String myRole = null;
        if(roles.size()==ADMIN ){
            myRole = "ADMIN";
        }
        if(roles.size() == TREASURE){
            myRole = "TREASURE";
        }
        if(roles.size()==STAFF){
            myRole = "STAFF";
        }
        Map roleOrIdMap = new HashMap();
        roleOrIdMap.put("role",myRole);
        roleOrIdMap.put("uid",uid);
        return roleOrIdMap;
    }

    //根据不同的用户获取不同的用户信息
    public ResultUtil getSomeLog(){
        Map userMap = getRole();
        String myRole = (String) userMap.get("role");
        String uid = (String) userMap.get("uid");
        ResultUtil resultUtil = new ResultUtil();
        List logs =null;
        if("ADMIN".equals(myRole)){
            //获取所有人的日志信息
            logs = (List) redisUtil.get("adminLogsSome");
            if(logs==null){
                logs = (List) redisUtil.get("adminLogsSome");
                if(logs==null){
                    synchronized (this){
                        logs = logMapper.selectSome();
                        redisUtil.set("adminLogsSome",logs);
                        redisUtil.expire("adminLogsSome",10);
                    }
                }
            }
        }else{
            logs = (List) redisUtil.get("userLogsSome");
            if(logs==null){
                logs = (List) redisUtil.get("userLogsSome");
                if(logs==null){
                    synchronized (this){
                        logs = logMapper.getSomeUserLog(uid);
                        redisUtil.set("userLogsSome",logs);
                        redisUtil.expire("userLogsSome",10);
                    }
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
        Map userMap = getRole();
        String myRole = (String) userMap.get("role");
        String uid = (String) userMap.get("uid");
        ResultUtil resultUtil = new ResultUtil();
        List allLogs = null;
        if("ADMIN".equals(myRole)){
            allLogs = (List) redisUtil.get("adminLogsAll");
            if(allLogs==null){
                allLogs = (List) redisUtil.get("adminLogsAll");
                if(allLogs==null){
                    allLogs = logMapper.selectAll();
                    redisUtil.set("addminLogsAll",allLogs);
                    redisUtil.expire("addminLogsAll",10);
                }
            }
        }else{
            allLogs = (List) redisUtil.get("userLogsAll");
            if(allLogs==null){
                allLogs = (List) redisUtil.get("userLogsAll");
                if(allLogs==null){
                    allLogs = logMapper.getAllUserLog(uid);
                    redisUtil.set("userLogsAll",allLogs);
                    redisUtil.expire("userLogsAll",10);
                }
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
