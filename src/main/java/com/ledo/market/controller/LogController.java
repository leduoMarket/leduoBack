package com.ledo.market.controller;

import com.ledo.market.mapper.LogMapper;
import com.ledo.market.service.LogService;
import com.ledo.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author 王梦琼
 * 为前台返回查询到的日志信息，
 * 提供查询前200条日志记录和查询所有的日志记录
 */

@RestController
public class LogController {
    @Resource
    LogService logService;
    //查询的时候默认查询前200条记录，查询当前用户的两百条记录
    @GetMapping("/someLog")
    public ResultUtil someLog(){
        return logService.getSomeLog();
    }
    @GetMapping("/allLog")
    public ResultUtil getAllLog(){
        return logService.getAllLogs();
    }
}