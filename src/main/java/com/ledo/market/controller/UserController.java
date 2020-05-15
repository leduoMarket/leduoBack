package com.ledo.market.controller;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    UserMapper usermapper;

    //这个是显示在employee界面上的所有数据
    @CrossOrigin
    @GetMapping("/admin/emps")
    public List<User> list(){
        return usermapper.selectAll();
    }

    //这个是写在register页面上的注册，向user表中新增数据
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

    //这个是在employyee界面进行更新操作
    @CrossOrigin
    @PostMapping("/editemp")
    @ResponseBody
    public StatusCodeResult updateuser(@RequestBody User requser) {
        System.out.println("UID:" + requser.getUserName());
        System.out.println("UID:" + requser.getUid());
        System.out.println(usermapper.updateByPrimaryKey(requser));
        return new StatusCodeResult(200);
    }

    //这个是在employee界面上删除数据
    @CrossOrigin
    @DeleteMapping("/delemp")
    public StatusCodeResult delemp(@RequestParam(value = "empId") String empId){
        System.out.println("empID:"+empId);
        System.out.println(usermapper.delete(empId));
        return new StatusCodeResult(200);
    }
    //这个是在loginuser界面修改密码操作
    @CrossOrigin
    @PostMapping("/updatepwd")
    @ResponseBody
    public StatusCodeResult updateuser(@RequestParam(value = "uid") String uid,@RequestParam(value = "pwd1") String pwd1,@RequestParam(value = "pwd2") String pwd2) {
        System.out.println("UID:" + uid);
        System.out.println("oldpwd:" + pwd1);
        System.out.println("newpwd:" + pwd2);
        System.out.println(usermapper.getUserByUid(uid,pwd1,pwd2));
        return new StatusCodeResult(200);
    }

    //这个是向loginuser界面传入信息
    @CrossOrigin
    @GetMapping("/getUserInfo")
    public User selectByPrimaryKey(@RequestParam(value = "uid") String uid){
        User s = usermapper.selectByPrimaryKey(uid);
        if(s!=null){
            System.out.println("uid"+s.getUid());
            System.out.println("password"+s.getPassword());
            return s;
        }
        return null;
    }
    //在权限界面显示数据
    @CrossOrigin
    @GetMapping("/getRole")
    public List<User> list1(){
        return usermapper.selectAll();
    }

    //在权限页面进行更改权限
    @CrossOrigin
    @PostMapping("/changeRole")
    @ResponseBody
    public StatusCodeResult updaterole(@RequestParam(value = "uid") String uid,@RequestParam(value = "urole") String urole,@RequestParam(value = "ustatus") Integer ustatus) {
        System.out.println("UID:" + uid);
        System.out.println("role:" + urole);
        System.out.println("status:" + ustatus);
        System.out.println(usermapper.getUserByRole(uid,urole,ustatus));
        return new StatusCodeResult(200);
    }
}
