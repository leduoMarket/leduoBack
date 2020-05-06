package com.ledo.market.controller;

import com.ledo.market.entity.Employee;
import com.ledo.market.mapper.EmployeeMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public List<Employee> list(Model model) {
        return employeeMapper.selectAll();
//        List<Employee> employees = employeeMapper.selectAll();
//        model.addAttribute("emps",employees);
//        return employees;
    }

    @CrossOrigin
    @PostMapping("/addemp")
    @ResponseBody
    public StatusCodeResult addemp(@RequestBody Employee reqemp) {
        System.out.print(reqemp.getEid());
        System.out.println(reqemp.getEname());
        System.out.println(employeeMapper.insert(reqemp));
        return new StatusCodeResult(200);

    }
}