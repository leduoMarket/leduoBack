package com.ledo.market.controller;
import com.ledo.market.entity.Employee;
import com.ledo.market.mapper.EmployeeMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
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
//    @CrossOrigin
//    @GetMapping("/delemp/{empId}")
//    public StatusCodeResult delemp(@PathVariable int empId){
//        System.out.println(employeeMapper.delete(empId));
//        return new StatusCodeResult(200);
//    }
}