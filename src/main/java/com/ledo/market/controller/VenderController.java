package com.ledo.market.controller;


import com.ledo.market.entity.Vender;
import com.ledo.market.result.StatusCodeResult;
import com.ledo.market.service.VenderService;
import com.ledo.market.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/staff")
public class VenderController {
    @Resource
    VenderService venderService;

    /**
     * 获取所有供应商的信息
     * */
    @GetMapping("/getAllVenders")
    public ResultUtil selectAll() {
        return venderService.getAllVenders();
    }

    /**
     * 增加某一个供应商
     * */
    @PostMapping("/addVender")
    @ResponseBody
    public ResultUtil addvender(@RequestBody Vender vender) {
        ResultUtil resultUtil = new ResultUtil();
        if (vender == null) {
            resultUtil.setCode(200);
            resultUtil.setMessage("需要增加的供应商信息不能为空");
        }
        return venderService.addVenderRecord(vender);
    }

    /**
     * 删除某一个供应商
     * */
    @DeleteMapping("/delVender")
    public ResultUtil delemp(@RequestParam(value = "venderId") Integer venderId) {
        ResultUtil resultUtil = new ResultUtil();
      if(venderId==null){
          resultUtil.setCode(201);
          resultUtil.setMessage("需要删除的员工号不能为空");
          return resultUtil;
      }
       return  venderService.delVenderRecord(venderId);
    }


}
