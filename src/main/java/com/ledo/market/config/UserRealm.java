package com.ledo.market.config;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

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
        String uid = upToken.getUsername();
        System.out.println("鉴权uid:"+uid);
        User user = userMapper.getUserByUid(uid);
        if(user==null){
            throw new UnknownAccountException("账户不存在");
        }
        Object principal = uid;
        Object credentials = user.getPassword();
        ByteSource credentialsSalt = ByteSource.Util.bytes(principal);
        Object passwordResult = new SimpleHash("MD5",credentials,credentialsSalt,99);
        System.out.println("数据库存储的密码："+passwordResult);
        return new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,getName());
    }



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String uid = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("授权uid:"+uid);
        Set<String> roles = new HashSet<>();
        roles.add("staff");
        System.out.println(roles);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        return info;
    }

}
