package com.ledo.market.controller;

import com.ledo.market.result.StatusCodeResult;
import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

/**
 * @author 王梦琼
 */

@RestController
public class LoginControl {
    @Autowired
    @Resource
    UserMapper userMapper;
    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public StatusCodeResult login(@RequestBody User postUser) {
        //获取需要管理的对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(postUser.getUserName(), postUser.getPassword()));
            System.out.println("登录成功!");
            return new StatusCodeResult(200);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败!");
            return new StatusCodeResult(400);
        }
    }
    @GetMapping("/user/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        return userMapper.getUserByName(userName);
    }
//    @GetMapping("/hello")
//    public String hello() {
//        return "hello";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "please login!";
//    }
//        String userName = HtmlUtils.htmlEscape(postUser.getUserName());
//        User user = userMapper.getUserByName(userName);
//        int SUCCESSFUL = 200;
//        int FAILED = 400;
//        if(user.equals(postUser)){
//            return new StatusCodeResult(SUCCESSFUL);
//        }else{
//            return new StatusCodeResult(FAILED);
//        }
}
