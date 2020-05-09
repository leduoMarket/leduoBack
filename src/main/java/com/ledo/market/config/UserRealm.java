package com.ledo.market.config;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @author 王梦琼
 * 自定义realm，继承AuthorizingRealm
 */
public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
    //鉴权,认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        String username = upToken.getUsername();
        User user = userMapper.getUserByName(username);
        if(user==null){
            throw new UnknownAccountException("账户不存在");
        }
        //传入的参数是什么，作用是什么?
        Object principal = username;
        //数据库中查询出的密码
        Object credentials = user.getPassword();
        return new SimpleAuthenticationInfo(principal,credentials,getName());
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        return null;
    }

}
