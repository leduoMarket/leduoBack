package com.ledo.market.controller;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.result.StatusCodeResult;
import com.ledo.market.service.UserService;
import com.ledo.market.utils.EncodingUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author 王梦琼
 * 对多个员工显示界面和当前登录员工的信息提供接口
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    ResultUtil resultUtil;
    @Resource
    UserMapper usermapper;
    /**
     * 在员工表面上展示所有员工信息数据
     * */
    @GetMapping("emps")
    public List<User> list(){
        return usermapper.selectAll();
    }

    /**
     * 根据传过来的员工编辑员工界面的信息
     * */
    @CrossOrigin
    @PostMapping("editemp")
    @ResponseBody
    public StatusCodeResult updateuser(@RequestBody User requser) {
        System.out.println(usermapper.updateUserInfo(requser));
        return new StatusCodeResult(200);
    }
    /**
     * 员工界面删除员工信息
     * */
    @DeleteMapping("delemp")
    public StatusCodeResult delemp(@RequestParam(value = "empId") String empId){
        System.out.println("empID:"+empId);
        System.out.println(usermapper.delete(empId));
        return new StatusCodeResult(200);
    }
    /**
     * 向当前登录user的User信息界面传递当前登录账号的信息
     * */
    @CrossOrigin
    @GetMapping("/getUserInfo")
    public User getLogingUserInfo(@RequestParam(value = "uid") String uid){
        User s = usermapper.selectCurrentUserInfo(uid);
        if(s!=null){
            System.out.println("uid"+s.getUid());
            System.out.println("password"+s.getPassword());
            return s;
        }
        return null;
    }
}
