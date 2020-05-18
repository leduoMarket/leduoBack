package com.ledo.market.controller;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.entity.User;
import com.ledo.market.service.UserService;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 控制用户的登录和注册，这两个请求不需要认证授权
 * @author 王梦琼
 */
@RestController
@Slf4j
public class LoginControl {
    @Resource
    UserService userService;
    @Resource
    ResultUtil resultUtil;
    @Resource
    UserMapper userMapper;
    private UsernamePasswordToken token = null;
    /**
     * 未授权定向到的接口
     * */
    @GetMapping("/noauth")
    @ResponseBody
    public ResultUtil authorized(){
        resultUtil.setCode(401);
        resultUtil.setMessage("未经授权，无法访问此页面");
        log.warn("系统被尝试未授权访问");
        return resultUtil;
    }
    /**
     * 向员工表里面插入新的员工，所有人都可以进行注册
     * 将获取到的user的密码取出来，进行二次加密，然后再set进user,最后插入数据库
     * */
    @PostMapping("/register")
    @ResponseBody
    public ResultUtil registeUser(@RequestBody User requser){
        if(requser==null){
            resultUtil.setCode(201);
            log.error("注册用户失败");
            resultUtil.setMessage("没有获取到需要注册的用户的信息");
            return  resultUtil;
        }
        return  userService.registe(requser);
    }
    @PostMapping("/login")
    @ResponseBody
    public ResultUtil login(@RequestBody User postUser) {
        Subject currentUser = SecurityUtils.getSubject();
        String uid = postUser.getUid();
        User user = userMapper.getUserByUid(uid);
        String userName = user.getUserName();
        String passwd = postUser.getPassword();
        if(!currentUser.isAuthenticated()){
            token = new UsernamePasswordToken(uid,passwd);
            try {
                currentUser.login(token);
            }catch (UnknownAccountException ue){
                resultUtil.setCode(401);
                resultUtil.setMessage("用户名不存在");
                return resultUtil;
            }catch (IncorrectCredentialsException ie){
                resultUtil.setCode(401);
                resultUtil.setMessage("密码错误");
                return resultUtil;
            }catch (LockedAccountException le){
                resultUtil.setCode(401);
                resultUtil.setMessage("用户被锁定");
                log.warn("-用户"+userName +"被锁定");
                return resultUtil;
            }catch(AuthenticationException ae){
                resultUtil.setCode(401);
                resultUtil.setMessage("其他登录异常");
                return resultUtil;
            }
        }
        final int ADMIN = 3;
        final int TREASURE = 2;
        final int STAFF = 1;
        Map resultMap = new HashMap();
       Set roles = userMapper.getRolesByuid(postUser.getUid());
        if(roles.size()==ADMIN){
            resultMap.put("role",1);
        }else if(roles.size()==TREASURE){
            resultMap.put("role",2);
        }else if(roles.size()==STAFF){
            resultMap.put("role",3);
        }
        resultMap.put("sessionId",currentUser.getSession().getId());
        resultUtil.setCode(200);
        resultUtil.setMessage("登录成功");
        log.info("-用户"+userName+" "+uid+"登录成功");
        resultUtil.setData(resultMap);
        return resultUtil;
    }
    /**
     * 执行修改密码的功能
     * */
    @GetMapping("/staff/changePassWd")
    @ResponseBody
    public ResultUtil changePassword(@RequestParam(value="uid") String uid, @RequestParam(value="pwd1") String pwd1, @RequestParam(value="pwd2") String pwd2) {
        return userService.changePassword(uid,pwd1,pwd2);
    }
    /**
    * 执行登出动作
    * */
    @GetMapping("/staff/logout")
    public ResultUtil logout(){
        ResultUtil resultUtil = new ResultUtil();
        Subject currentUser = SecurityUtils.getSubject();
        log.info("-用户"+currentUser.getPrincipal()+"退出登录");
        currentUser.logout();
        resultUtil.setCode(405);
        resultUtil.setMessage("执行了登出功能");
        //执行退出登录
        return resultUtil;
    }
    /**
     * author:王梦琼
     * 如果权限不对就返回到原来的登录页面
     * **/
    @GetMapping("/identifyFailed")
    @ResponseBody
    public ResultUtil identifyFailed(){
       resultUtil.setCode(401);
       resultUtil.setMessage("未登录");
       log.warn("系统被尝试未登录访问");
        return resultUtil;
    }
    /**
     * 向当前登录user的User信息界面传递当前登录账号的信息
     * */
    @GetMapping("/staff/getCurrentUserMessage")
    public ResultUtil getLogingUserInfo(@RequestParam(value = "uid") String uid){
        ResultUtil resultUtil = new ResultUtil();
        if(uid==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("无法查看到员工号为空的员工的信息");
            return resultUtil;
        }
        return userService.getCurrentUserInfo(uid);
    }
}
