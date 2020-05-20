package com.ledo.market.controller;


import com.ledo.market.entity.ProductReturn;
import com.ledo.market.service.ProductReturnService;
import com.ledo.market.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


@RestController
@RequestMapping("/staff")
public class ProductReturnController {
    @Resource
    ProductReturnService productReturnService;
    @GetMapping("/productReturnList")
    public ResultUtil selectAll(){
        return productReturnService.getAll();
    }

    @PostMapping("/addProductReturn")
    @ResponseBody
    public ResultUtil addProductReturn(@RequestBody ProductReturn returnRecord){
        ResultUtil  resultUtil = new ResultUtil();
        if(returnRecord==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("需要插入的退货单记录不能为空");
            return resultUtil;
        }
        return productReturnService.addRecord(returnRecord);
    }
    @DeleteMapping("/delProductReturn")
    public ResultUtil delemp(@RequestParam(value = "gid") Long gid) {
        ResultUtil  resultUtil = new ResultUtil();
      if(gid==null){
          resultUtil.setCode(201);
          resultUtil.setMessage("被删除的退货单号不能为空");
          return resultUtil;
      }
      return productReturnService.delRecord(gid);
    }
}
