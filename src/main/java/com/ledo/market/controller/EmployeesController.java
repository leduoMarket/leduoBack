package com.ledo.market.controller;
import com.ledo.market.entity.Employee;
import com.ledo.market.mapper.EmployeeMapper;
import com.ledo.market.result.StatusCodeResult;
import com.ledo.market.utils.RedisUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王梦琼
 */
@RestController
@RequestMapping("/admin")
public class EmployeesController {
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    RedisUtil redisUtils;
    @GetMapping("/emps")
    public List<Employee> list(){
        //用锁解决高并发时候存在缓存穿透问题
        List<Employee>employees= (List<Employee>) redisUtils.get("employeeList");
        if(employees==null){
            synchronized (this){
                employees= (List<Employee>) redisUtils.get("employeeList");
                if(employees==null){
                    System.out.println("查询数据库==========================");
                    employees = employeeMapper.selectAll();
                    redisUtils.set("employeeList",employees);
                }else{
                    System.out.println("高并发下查询缓存==========================");
                }
            }
        }else{
            System.out.println("查询缓存==========================");
        }
        return employeeMapper.selectAll();
    }
    @PostMapping("/addemp")
    @ResponseBody
    public StatusCodeResult addemp(@RequestBody Employee reqemp){
        System.out.print(reqemp.getEid());
        System.out.println(reqemp.getEname());
        System.out.println(employeeMapper.insert(reqemp));
        return new StatusCodeResult(200);
    }

    @DeleteMapping("/delemp")
    public StatusCodeResult delemp(@RequestParam(value = "empId") int empId){
        System.out.println("empID:"+empId);
        System.out.println(employeeMapper.delete(empId));
        return new StatusCodeResult(200);
    }
}