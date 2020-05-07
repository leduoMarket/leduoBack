package com.ledo.market.controller;
import com.ledo.market.entity.Employee;
import com.ledo.market.mapper.EmployeeMapper;
import com.ledo.market.result.StatusCodeResult;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

=======
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
>>>>>>> b80ddc63fab58925414cb117d447f8412ab01833
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
    public List<Employee> list(Model model) {
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

    @CrossOrigin
    @PostMapping("/addemp")
    @ResponseBody
    public StatusCodeResult addemp(@RequestBody Employee reqemp) {
        System.out.print(reqemp.getEid());
        System.out.println(reqemp.getEname());
        System.out.println(employeeMapper.insert(reqemp));
        return new StatusCodeResult(200);

    }

    @CrossOrigin
    @DeleteMapping("/delemp")
    public StatusCodeResult delemp(@RequestParam(value = "empId") int empId) {
        System.out.println("empID:" + empId);
        System.out.println(employeeMapper.delete(empId));
        return new StatusCodeResult(200);
    }
}