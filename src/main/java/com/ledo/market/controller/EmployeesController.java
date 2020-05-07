package com.ledo.market.controller;
import com.ledo.market.entity.Employee;
import com.ledo.market.mapper.EmployeeMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;
/**
 * @author 王梦琼
 */
@RestController
public class EmployeesController {
    @Resource
    EmployeeMapper employeeMapper;
    @CrossOrigin
    @GetMapping("/emps")
    public List<Employee> list(){
        return employeeMapper.selectAll();
    }

    @CrossOrigin
    @PostMapping("/addemp")
    @ResponseBody
    public StatusCodeResult addemp(@RequestBody Employee reqemp){
        System.out.print(reqemp.getEid());
        System.out.println(reqemp.getEname());
        System.out.println(employeeMapper.insert(reqemp));
        return new StatusCodeResult(200);
    }

    @CrossOrigin
    @DeleteMapping("/delemp")
    public StatusCodeResult delemp(@RequestParam(value = "empId") int empId){
        System.out.println("empID:"+empId);
        System.out.println(employeeMapper.delete(empId));
        return new StatusCodeResult(200);
    }
}