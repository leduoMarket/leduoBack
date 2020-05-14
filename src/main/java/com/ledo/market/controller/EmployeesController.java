package com.ledo.market.controller;
import com.ledo.market.entity.Employee;
import com.ledo.market.mapper.EmployeeMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author 王梦琼
 */
@RestController
@RequestMapping("/admin")
public class EmployeesController {
    @Resource
    EmployeeMapper employeeMapper;

    @GetMapping("/emps")
    public List<Employee> list(){
        return employeeMapper.selectAll();
    }
}