package com.ledo.market.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author 王梦琼
 * 自定义realm，继承AuthorizingRealm
 */
public class UserRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");

        return null;
    }
    //鉴权
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取认证规则
        System.out.println("执行认证");
        String username = (String)token.getPrincipal();
        if(!"wmq".equals(username)){
            throw new UnknownAccountException("账户不存在");
        }
        return new SimpleAuthenticationInfo(username,"123456",getName());
    }
}
