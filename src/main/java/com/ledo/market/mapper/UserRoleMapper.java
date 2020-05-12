package com.ledo.market.mapper;


import com.ledo.market.entity.UserRole;

import java.util.List;
public interface UserRoleMapper {
    int insert(UserRole record);
    UserRole selectByPrimaryKey(Integer urid);
    List<UserRole> selectAll();
    int updateByPrimaryKey(UserRole record);

}