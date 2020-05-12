package com.ledo.market.mapper;

import com.ledo.market.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author 王梦琼
 */
@Mapper
public interface UserMapper {
    public User getUserByUid(String uid);
    public Set<String> getRolesByuid(String uid);
}
