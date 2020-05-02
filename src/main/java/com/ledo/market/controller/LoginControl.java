package com.ledo.market.controller;

import com.ledo.market.result.StatusCodeResult;
import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
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
    public StatusCodeResult login(@RequestBody User postUser){
        String userName = HtmlUtils.htmlEscape(postUser.getUserName());
        User user = userMapper.getUserByName(userName);
        int SUCCESSFUL = 200;
        int FAILED = 400;
        if(user.equals(postUser)){
            return new StatusCodeResult(SUCCESSFUL);
        }else{
            return new StatusCodeResult(FAILED);
        }
    }
    @GetMapping("/user/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        return userMapper.getUserByName(userName);
    }
}
