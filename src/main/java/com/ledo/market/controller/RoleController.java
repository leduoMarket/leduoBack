package com.ledo.market.controller;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
/**
 * 对于权限界面的信息进行显示
 * */
@RestController
public class RoleController {
    @Resource
    UserMapper usermapper;
    /**
     * 在权限界面显示数据
     * */
    @GetMapping("/getRole")
    public List<User> list1(){
        return usermapper.selectAll();
    }
    /**
     *根据员工号更新员工角色和账号的锁定状态
     * */
    @CrossOrigin
    @PostMapping("/changeRole")
    @ResponseBody
    public StatusCodeResult updaterole(@RequestParam(value = "uid") String uid, @RequestParam(value = "uRole") String urole, @RequestParam(value = "uStatus") Integer ustatus) {
        System.out.println(usermapper.updateRoleStatusByUid(uid,urole,ustatus));
        return new StatusCodeResult(200);
    }

}
