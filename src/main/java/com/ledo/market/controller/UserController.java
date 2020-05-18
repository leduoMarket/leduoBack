package com.ledo.market.controller;
import com.ledo.market.entity.User;
import com.ledo.market.service.UserService;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

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
    @PutMapping("updatePhoneOrName")
    public ResultUtil updatePhoneOrName(@RequestBody User user){
        String uid = user.getUid();
        String userName = user.getUserName();
        String phone = user.getPhone();
        ResultUtil resultUtil = new ResultUtil();
        if(uid==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("修改的用户名为空，无法进行修改");
            return resultUtil;
        }
        return userService.updatePhoneOrName(uid,userName,phone);
    }
    /**
     * 在员工表面上展示所有员工信息数据
     * */
    @GetMapping("getAllemployees")
    public ResultUtil list(){
        ResultUtil resultUtil = new ResultUtil();
        return userService.getAllemps();
    }
    /**
     * 根据传过来的员工编辑员工界面的信息
     * */
    @PutMapping("changeStatus")
    @ResponseBody
    public ResultUtil updateuser(@RequestBody User user){
        //@RequestParam(value = "uid") String uid,@RequestParam(value = "role") String role,@RequestParam(value = "status") Integer status
        String uid = user.getUid();
        String role = user.getRole();
        Integer status = user.getStatus();
        System.out.println("uid:"+uid+"role:"+role+" status:"+status);
        ResultUtil resultUtil = new ResultUtil();
        if(uid==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("被编辑的用户不能为空");
            return resultUtil;
        }
       return userService.editStatusOrRole(uid,role,status);
    }

    /**
     * 员工界面删除员工信息
     * */
    @DeleteMapping("delemp")
    public ResultUtil delemp(@RequestParam(value = "empId") String empId){
        ResultUtil resultUtil = new ResultUtil();
        if(empId==null){
           resultUtil.setCode(201);
           resultUtil.setMessage("被删除的员工号为空");
       }
       return userService.deleteEmployee(empId);
    }
}
