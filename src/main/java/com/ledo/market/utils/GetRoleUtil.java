package com.ledo.market.utils;

import com.ledo.market.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 获取当前用户的角色信息
 * */
@Component
public class GetRoleUtil {
    @Resource
    UserMapper userMapper;

    public String getRole(){
        final int ADMIN = 3;
        final int TREASURE = 2;
        final int STAFF = 1;
        Subject currentUser = SecurityUtils.getSubject();
        Object uid = currentUser.getPrincipal();
        Set<String> roles = userMapper.getRolesByuid(uid.toString());
        if(roles.size()==ADMIN){
            return "ADMIN";
        }
        if(roles.size()==TREASURE){
            return "TREASURE";
        }
        return "STAFF";
    }
}
