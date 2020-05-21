package com.ledo.market.service;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.mapper.UserRoleMapper;
import com.ledo.market.utils.EncodingUtil;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王梦琼
 */
@Service
@Slf4j
public class UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    RedisUtil redisUtil;
    @Resource
    UserRoleMapper userRoleMapper;
    //注册和修改密码时候用到的加密工具
    private EncodingUtil encodingUtil = new EncodingUtil();

    /**
     * 修改电话号码或者是姓名的服务类
     * */
    public ResultUtil updatePhoneOrName(String uid,String name,String phone){
        ResultUtil resultUtil = new ResultUtil();
        int influentLine = 0;
        influentLine = userMapper.updatePhoneOrName(uid,name,phone);
        if(influentLine==0){
            resultUtil.setCode(201);
            resultUtil.setMessage("修改失败");
            return  resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("修改成功");
        return  resultUtil;
    }


    public ResultUtil getCurrentUserInfo(String uid){
        ResultUtil resultUtil = new ResultUtil();
        User user = userMapper.selectCurrentUserInfo(uid);
        if(user==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("获取登录用户信息失败");
        };
        resultUtil.setCode(200);
        resultUtil.setMessage("获取登录用户信息成功");
        resultUtil.setData(user);
        return resultUtil;
    }

    /**
     * 根据员工号删除对应的员工
     * */
    public ResultUtil deleteEmployee(String uid){
        ResultUtil resultUtil = new ResultUtil();
        System.out.println(uid);
        userMapper.delete(uid);
        userMapper.delete(uid);
        //删除员工的同时将角色表中的信息也进行删除
        userRoleMapper.deleteByUid(uid);
        resultUtil.setCode(200);
        resultUtil.setMessage("删除员工信息成功");
        log.warn("-员工号为"+uid+"的员工被删除");
        return resultUtil;
    }
    /**
     * 根据员工号改变用户的角色和状态,修改的时候不支持缓存功能
     * */
    public ResultUtil editStatusOrRole(String uid,String role,Integer status){
        User user1 = userMapper.getUserByUid(uid);
        //如果修改状态时候的用户角色和注册的时候不一样，则存在注册用户使用权限不符合的情况，
        //需要将用户在user_role表里面的权限删除，然后再插入新的角色
        if(!user1.getRole().equals(role)){
            userRoleMapper.deleteByUid(uid);
            if(role.equals("管理员")){
                userRoleMapper.addAdmin(uid);
                userRoleMapper.addStaff(uid);
                userRoleMapper.addTreasure(uid);
            }
            if(role.equals("财务")){
                userRoleMapper.addTreasure(uid);
                userRoleMapper.addStaff(uid);
            }
            if(role.equals("员工")){
                userRoleMapper.addStaff(uid);
            }
            log.info("-用户"+uid+"注册时候的权限为："+user1.getRole()+"修改为"+role);
        }
        ResultUtil resultUtil = new ResultUtil();
        int includeLine = 0;
        includeLine = userMapper.updateRoleStatusByUid(uid,role,status);
        if(includeLine==0){
            resultUtil.setCode(201);
            resultUtil.setMessage("修改失败");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("修改成功");
        return resultUtil;
    }

    public ResultUtil getAllemps(){
        ResultUtil resultUtil = new ResultUtil();
        List<User> userList = (List<User>) redisUtil.get("userList");
        if(userList==null){
            userList = (List<User>) redisUtil.get("userList");
            synchronized (this){
                if(userList==null){
                    userList = userMapper.selectAll();
                }
            }
        }
        if(userList==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("暂时没有任何用户哦");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("成功获取到员工信息");
        resultUtil.setData(userList);
        return  resultUtil;
    }
    /**
     * 根据传过来的用户名和密码修改密码，前面输入过来的面需要做过滤,修改密码不进行redis缓存操作
     * 如果新密码和原密码相同则返回，否则将新密码加密存储进数据库
     * */
    public ResultUtil changePassword(String uid,String oldPassWd,String newPassWd){
        ResultUtil resultUtil = new ResultUtil();
        Subject currentUser = SecurityUtils.getSubject();
        if(!uid.equals(currentUser.getPrincipal())){
            resultUtil.setCode(201);
            resultUtil.setMessage("你没有权限修改该用户的密码");
            return resultUtil;
        }
        int influenceLine = 0;
        if(oldPassWd.equals(newPassWd)){
            resultUtil.setCode(201);
            resultUtil.setMessage("新密码和原密码相同");
            log.error("-用户"+uid+"打算修改的密码与原密码相同");
            return resultUtil;
        }

        //传过来的密码是在前端进行简单的md5加密过后的密码
        User user1 = new User();
        user1.setUid(uid);
        user1.setPassword(oldPassWd);
        Object encodedPassWd = encodingUtil.getPasswordEncoding(user1);
        String passwordFromBD = userMapper.getPasswordByuid(uid);
        if(!passwordFromBD.equals(encodedPassWd.toString())){
            resultUtil.setCode(201);
            resultUtil.setMessage("原密码输入不正确");
            return  resultUtil;
        }

        //将新密码进行二次加密之后存储进入数据库，加密的时候需要用到uid和密码
        User userWithNewPassword = new User();
        userWithNewPassword.setUid(uid);
        userWithNewPassword.setPassword(newPassWd);
        Object encodedNewPassWord = encodingUtil.getPasswordEncoding(userWithNewPassword);
        //将加密好的密码存储进入数据库
        influenceLine = userMapper.changePassWord(uid,encodedNewPassWord.toString());
        if(influenceLine==0){
            resultUtil.setCode(201);
            resultUtil.setMessage("修改密码失败");
            log.error("-用户"+uid+"修改密码失败");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("修改密码成功");
        log.info("-用户"+uid+"修改密码成功");
        return resultUtil;
    }

    /**
     * 为注册函数提供服务
     * */
    public ResultUtil registe(User user){
        ResultUtil resultUtil = new ResultUtil();
        //判断数据库数是否已经存在这个用户
        User user1 = userMapper.getUserByUid(user.getUid());
        if(user1!=null){
            resultUtil.setCode(201);
            resultUtil.setMessage("该账户已经注册过");
            log.warn("-尝试注册一个已经存在的用户");
            return resultUtil;
        }
        Object encodedPasswd = encodingUtil.getPasswordEncoding(user);
        user.setPassword(encodedPasswd.toString());
        //插入用户的时候需要经过缓存吗，
        if(userMapper.insert(user)==0){
            resultUtil.setCode(201);
            resultUtil.setMessage("注册用户失败");
            log.error("-用户注册失败");
            return resultUtil;
        };
        //根据用户的填写的角色在user_role表里面增加信息
        if(user.getRole().equals("管理员")){
            log.info("-正在被注册的用户id为："+user.getUid());
            userRoleMapper.addAdmin(user.getUid());
            userRoleMapper.addStaff(user.getUid());
            userRoleMapper.addTreasure(user.getUid());
        }
        if(user.getRole().equals("财务")){
            userRoleMapper.addTreasure(user.getUid());
            userRoleMapper.addStaff(user.getUid());
        }
        if(user.getRole().equals("员工")){
            userRoleMapper.addStaff(user.getUid());
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("注册用户成功");
        log.info("-成功注册账号为"+user.getUid()+"的用户");
        return resultUtil;
    }
}
