package com.ledo.market.config;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import javax.annotation.Resource;
import java.util.Set;
/**
 * @author 王梦琼
 * 自定义realm，继承AuthorizingRealm
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
    //鉴权,认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        char[] password = upToken.getPassword();
        String uid = upToken.getUsername();
        System.out.println("鉴权uid:"+uid);
        User user = userMapper.getUserByUid(uid);
        if(user==null){
            throw new UnknownAccountException("账户不存在");
        }
        if(user.getStatus()==0){
            throw  new LockedAccountException("账户被锁定，不能登录");
        }
        Object principal = uid;
        Object credentials = user.getPassword();
        ByteSource credentialsSalt = ByteSource.Util.bytes(principal);
        Object passwordResult = new SimpleHash("MD5",password,credentialsSalt,99);
        return new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String uid = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名联合查询出数据库中该用户名拥有的权限
        Set<String> roles = userMapper.getRolesByuid(uid);
        System.out.println("数据库查询出来的角色有："+roles);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        return info;
    }
}
