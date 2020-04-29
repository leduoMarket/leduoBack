package com.ledo.market.controller;

import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * @author 王梦琼
 */
@RestController
public class HelloController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Resource
    UserMapper userMapper;
//默认配置的操作数据库的操作
    @ResponseBody
    @GetMapping(value = "/query")
    public Map<String,Object> map(){
       List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from user ");
       return list.get(0);
    }
    @GetMapping("/user/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        return userMapper.getUserByName(userName);
    }
}
