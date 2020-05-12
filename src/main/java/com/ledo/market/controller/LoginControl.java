package com.ledo.market.controller;
import com.ledo.market.result.StatusCodeResult;
import com.ledo.market.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录失败跳到登录页面
 * 登录成功跳到home页面
 * @author 王梦琼
 */
@RestController
public class LoginControl {
    @CrossOrigin
    @GetMapping("/noauth")
    @ResponseBody
    public String authorized(){
        return "未经授权，无法访问此页面";
    }
    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public StatusCodeResult login(@RequestBody User postUser) {
        Subject currentUser = SecurityUtils.getSubject();
        String uid = postUser.getUid();
        String passwd = postUser.getPassword();
        System.out.println("uid:"+uid);
        System.out.println("password:"+passwd);
        //将前端传过来的数据传进token进行保存
        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(uid,passwd);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            }catch (UnknownAccountException ue){
                //用户名不存在
                return new StatusCodeResult(401);
            }catch (IncorrectCredentialsException ie){
                //密码错误
                return new StatusCodeResult(402);
            }catch (LockedAccountException le){
                //用户被锁定
                return new StatusCodeResult(403);
            }catch(AuthenticationException ae){
                //其他登录异常
                return new StatusCodeResult(404);
            }
        }
        return new StatusCodeResult(200);
    }
    /**
    * 执行登出动作
    * */
    @CrossOrigin
    @GetMapping("/logout")
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
