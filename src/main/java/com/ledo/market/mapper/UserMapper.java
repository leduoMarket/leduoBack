package com.ledo.market.mapper;

import com.ledo.market.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

import java.util.Set;

/**
 * @author 王梦琼
 */
@Mapper
public interface UserMapper {
    int getUserByUid(String uid,String pwd1,String pwd2);
    int getUserByRole(String uid,String urole,Integer ustatus);
    int insert(User record);
    int updateByPrimaryKey(User record);
    List<User> selectAll();
    User selectByPrimaryKey(String uid);
    String delete(String uid);
}


