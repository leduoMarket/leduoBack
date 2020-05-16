package com.ledo.market.service;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import com.ledo.market.mapper.UserRoleMapper;
import com.ledo.market.utils.EncodingUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
@Slf4j
public class UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    ResultUtil resultUtil;
    @Resource
    UserRoleMapper userRoleMapper;
    EncodingUtil encodingUtil = new EncodingUtil();
    /**
     * 根据传过来的用户名和密码修改密码，前面输入过来的面需要做过滤,修改密码不进行redis缓存操作
     * 如果新密码和原密码相同则返回，否则将新密码加密存储进数据库
     * */
    public ResultUtil changePassword(String uid,String oldPassWd,String newPassWd){
        int influenceLine = 0;
        if(oldPassWd.equals(newPassWd)){
            resultUtil.setCode(201);
            resultUtil.setMessage("新密码和原密码相同");
            return resultUtil;
        }
        //传过来的密码是在前端进行简单的md5加密过后的密码
        User user1 = new User();
        user1.setUid(uid);
        user1.setPassword(newPassWd);
        Object encodedPassWd = encodingUtil.getPasswordEncoding(user1);

        //将加密好的密码打算存储进入数据库
        influenceLine = userMapper.changePassWord(uid,encodedPassWd.toString());
        if(influenceLine==0){
            resultUtil.setCode(201);
            resultUtil.setMessage("修改密码失败");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setMessage("修改密码成功");
        log.info("-修改密码成功");
        return resultUtil;
    }
    /**
     * 为注册函数提供服务
     * */
    public ResultUtil registe(User user){
        //判断数据库数是否已经存在这个用户
        User user1 = userMapper.getUserByUid(user.getUid());
        if(user1!=null){
            resultUtil.setCode(201);
            resultUtil.setMessage("该账户已经注册过");
            return resultUtil;
        }
        Object encodedPasswd = encodingUtil.getPasswordEncoding(user);
        log.info("-数据库实际存储的密码为："+encodedPasswd);
        user.setPassword(encodedPasswd.toString());
        //插入用户的时候需要经过缓存吗，
        if(userMapper.insert(user)==0){
            resultUtil.setCode(201);
            resultUtil.setMessage("注册用户失败");
            return resultUtil;
        };
        //根据用户的填写的角色在user_role表里面增加信息
        if(user.getRole().equals("管理员")){
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
        return resultUtil;
    }
}
