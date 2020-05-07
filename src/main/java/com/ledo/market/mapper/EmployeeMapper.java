package com.ledo.market.mapper;

import com.ledo.market.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    int insert(Employee record);
    List<Employee> selectAll();
    Integer delete(int eid);
    Employee selectByPrimaryKey(Integer eid);
}