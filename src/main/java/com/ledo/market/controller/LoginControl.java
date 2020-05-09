package com.ledo.market.controller;

import com.ledo.market.result.StatusCodeResult;
import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

/**
 * 登录失败跳到登录页面
 * 登录成功跳到home页面
 * @author 王梦琼
 */

@RestController
public class LoginControl {
    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public StatusCodeResult login(@RequestBody User postUser) {
        //只有在currentUser.login登录成功后才可以，
        // 获取需要认证的对象，在哪里都可以获取到
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println("currentUser:"+currentUser);
        //当前subject未进行登录
        String username = postUser.getUserName();
        String passwd = postUser.getPassword();
        System.out.println("username:"+username);
        System.out.println("passwd:"+passwd);
        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,passwd);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            }catch (UnknownAccountException ue){
                //用户名不存在
                return new StatusCodeResult(401);
            }catch (IncorrectCredentialsException ie){
                //密码错误
                return new StatusCodeResult(402);
            }catch (LockedAccountException le){
                //用户被锁定
                return new StatusCodeResult(403);
            }catch(AuthenticationException ae){
                //其他登录异常
                return new StatusCodeResult(404);
            }
        }

        System.out.println("currentUser:"+currentUser);
        return new StatusCodeResult(200);
    }
    @CrossOrigin
    @GetMapping("/logout")
    public StatusCodeResult logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        //执行退出登录
        return new StatusCodeResult(405);
    }
}
