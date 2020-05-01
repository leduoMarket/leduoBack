package com.ledo.market.controller;

import com.ledo.market.Result;
import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

/**
 * @author 王梦琼
 */

@RestController
public class Control {
    @Autowired
    @Resource
    UserMapper userMapper;
    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User postUser){
        System.out.println(postUser);
        LoginService loginService = new LoginService();
        String userName = HtmlUtils.htmlEscape(postUser.getUserName());
        User user = userMapper.getUserByName(userName);
       return loginService.loginTest(user,postUser);
    }
    @GetMapping("/user/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        return userMapper.getUserByName(userName);
    }
}
