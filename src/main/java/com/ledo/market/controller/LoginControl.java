package com.ledo.market.controller;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.result.StatusCodeResult;
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
    @GetMapping("/noauth")
    @ResponseBody
    public Map<String,Object> authorized(){
        System.out.println("未经授权，无法访问此页面");
        Map map= new HashMap();
        map.put("code",400);
        map.put("message","未经授权，无法访问此页面");
        return map;
    }
    /**
     * 向员工表里面插入新的员工，所有人都可以进行注册
     * 将获取到的user的密码取出来，进行二次加密，然后再set进user,最后插入数据库
     * */
    @PostMapping("/register")
    @ResponseBody
    public ResultUtil adduser(@RequestBody User requser){
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
    public Map<String,Object> login(@RequestBody User postUser) {
        Subject currentUser = SecurityUtils.getSubject();
        String uid = postUser.getUid();
        String passwd = postUser.getPassword();
       log.info("login接口中尝试登录的用户："+uid);
       log.info("login中其密码为："+passwd);
        Map resultMap = new HashMap();
        if(!currentUser.isAuthenticated()){
            token = new UsernamePasswordToken(uid,passwd);
            try {
                currentUser.login(token);
            }catch (UnknownAccountException ue){
                resultMap.put("code",401);
                resultMap.put("msg","用户名不存在");
                return resultMap;
            }catch (IncorrectCredentialsException ie){
                resultMap.put("code",402);
                resultMap.put("msg","密码错误");
                return resultMap;
            }catch (LockedAccountException le){
                resultMap.put("code",403);
                resultMap.put("msg","用户被锁定");
                return resultMap;
            }catch(AuthenticationException ae){
                resultMap.put("code",404);
                resultMap.put("msg","其他登录异常");
                return resultMap;
            }
        }

        final int ADMIN = 3;
        final int TREASURE = 2;
        final int STAFF = 1;
       Set roles = userMapper.getRolesByuid(postUser.getUid());
        if(roles.size()==ADMIN){
            resultMap.put("role",1);
        }else if(roles.size()==TREASURE){
            resultMap.put("role",2);
        }else if(roles.size()==STAFF){
            resultMap.put("role",1);
        }
        resultMap.put("code",200);
        resultMap.put("sessionId",currentUser.getSession().getId());
        return  resultMap;
    }
    /**
     * 执行修改密码的功能
     * */
    @PostMapping("/staff/changePassWd")
    @ResponseBody
    public ResultUtil changePassword(@RequestParam(value="uid") String uid, @RequestParam(value="pwd1") String pwd1, @RequestParam(value="pwd2") String pwd2) {
        return userService.changePassword(uid,pwd1,pwd2);
    }
    /**
    * 执行登出动作
    * */
    @CrossOrigin
    @GetMapping("/staff/logout")
    public StatusCodeResult logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        System.out.println("执行了登出功能");
        //执行退出登录
        return new StatusCodeResult(405);
    }
    /**
     * author:王梦琼
     * 如果权限不对就返回到原来的登录页面
     * **/
    @CrossOrigin
    @GetMapping("/identifyFailed")
    @ResponseBody
    public Object identifyFailed(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code","100");
        map.put("msg","未登录");
        return map;
    }
}
