package com.ledo.market.services;
import com.ledo.market.Result;
import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王梦琼
 */ //service标签是干什么的？


public class LoginService {
    public  Result loginTest(User user,User postUser){
        System.out.print("进入到Service页面");
        int SUCCESSFUL = 200;
        int FAILED = 400;
        System.out.print("user"+user.getUserName());
        System.out.print("postUser"+postUser.getUserName());
        if(user.equals(postUser)){
            System.out.print("\n相等：user"+user.getUserName());
             return new Result(SUCCESSFUL);
        }else{
            System.out.print("\nuser"+user.getUserName());
             return new Result(FAILED);
        }
    }
}
