package com.ledo.market.controller;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.result.StatusCodeResult;
import com.ledo.market.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 登录失败跳到登录页面
 * 登录成功跳到home页面
 * @author 王梦琼
 */
@RestController
public class LoginControl {
    @Resource
    UserMapper userMapper;
    @GetMapping("/noauth")
    @ResponseBody
    public Map<String,Object> authorized(){
        System.out.println("未经授权，无法访问此页面");
        Map map= new HashMap();
        map.put("code",400);
        map.put("message","未经授权，无法访问此页面");
        return map;
    }
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@RequestBody User postUser) {
        Subject currentUser = SecurityUtils.getSubject();
        String uid = postUser.getUid();
        String passwd = postUser.getPassword();
        System.out.println("uid:"+uid);
        System.out.println("password:"+passwd);
        UsernamePasswordToken token = null;
        Map resultMap = new HashMap();
        if(!currentUser.isAuthenticated()){
            token = new UsernamePasswordToken(uid,passwd);
//            token.setRememberMe(true);
            try {
                currentUser.login(token);
                System.out.println("currentUserSession:"+currentUser.getSession().getId());
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
       Set roles = userMapper.getRolesByuid(postUser.getUid());
        if(roles.size()==3){
            resultMap.put("role",1);
        }else if(roles.size()==2){
            resultMap.put("role",2);
        }else if(roles.size()==1){
            resultMap.put("role",1);
        }
        System.out.println(currentUser.getSession().getId());
        resultMap.put("code",200);
        resultMap.put("sessionId",currentUser.getSession().getId());
        return  resultMap;
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
