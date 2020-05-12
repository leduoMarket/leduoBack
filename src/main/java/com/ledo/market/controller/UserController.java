package com.ledo.market.controller;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    UserMapper usermapper;
    @CrossOrigin
    @PostMapping("/addregister")
    @ResponseBody
    public StatusCodeResult adduser(@RequestBody User requser){
        //System.out.print(requser.getUid());
        System.out.println(requser.getUid());
        System.out.println(requser.getSatatus());
        System.out.println(requser.getPassword());
        System.out.println(requser.getPhone());
        System.out.println(requser.getRole());
        System.out.println(requser.getUserName());
        System.out.println(usermapper.insert(requser));
        return new StatusCodeResult(200);
    }
}
