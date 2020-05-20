package com.ledo.market.mapper;

import com.ledo.market.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;

import java.util.Date;
import java.util.List;

import java.util.Set;

/**
 * @author 王梦琼
 */
@Mapper
public interface UserMapper {

    Integer updatePhoneOrName(String uid,String userName,String phone);

    /**
     * 根据员工号获取到员工，用于登录流程使用
     * */
    User getUserByUid(String uid);
    /**
     * 根据员工号获取用户所拥有的角色信息，用于角色授权使用
     * @param uid  传入的员工编号
     * @return  返回该员工拥有的角色集合
     * */
    Set<String> getRolesByuid(String uid);

     /**
     * 注册流程实际是在向user表中插入一条数据
     * */
    int insert(User record) throws DuplicateKeyException;


    /**
     * 修改员工密码
     * @param pwd2 新密码
     * @param uid 传入的员工号
     * @return 返回修改密码影响的行数
     * */
    int changePassWord(String uid,String pwd2);
    /**
     * 根据员工号更新员工的角色和账号锁定状态
     * */
    int updateRoleStatusByUid(String uid,String urole,Integer ustatus);

    /**
     * 查询出所有员工的员工信息
     * */
    List<User> selectAll();
    /**
     * 获取当前登录账号的员工信息
     * */
    User selectCurrentUserInfo(String uid);
    /**
     * 员工界面删除员工信息
     * */
    Integer delete(String uid);

    String getPasswordByuid(String uid);
}


