package com.ledo.market.controller;

import com.ledo.market.entity.Employee;
import com.ledo.market.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

/**
 * @author 王梦琼
 */
@RestController
public class EmployeesController {
    @Autowired
    @Resource
    EmployeeMapper employeeMapper;
    @CrossOrigin
    @GetMapping("/emps")
    public List<Employee> list(Model model){
        return employeeMapper.selectAll();
//        List<Employee> employees = employeeMapper.selectAll();
//        model.addAttribute("emps",employees);
//        return employees;
    }
}