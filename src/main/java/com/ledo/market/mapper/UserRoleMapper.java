package com.ledo.market.mapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * 员工和角色的对应表，当新增员工的时候，根据员工的角色在这个表中增加相应的角色
 * */
@Mapper
public interface UserRoleMapper {
    /**
     * 如果是管理员则默认在User_role表里面增加rid为3的记录
     * */
    Integer addAdmin(String uid);
    /**
     * 如果是员工则默认在User_role表里面增加rid为1的记录
     * */
    Integer addStaff(String uid);
    /**
     * 如果是财务则默认在User_role表里面增加rid为2的记录
     * */
    Integer addTreasure(String uid);

    Integer deleteByUid(String uid);
}