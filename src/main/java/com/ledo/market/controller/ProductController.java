package com.ledo.market.controller;

import com.ledo.market.entity.Product;
import com.ledo.market.service.ProductService;
import com.ledo.market.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**商品表
 * @author lenovo**/
@RestController
@RequestMapping("/staff")
public class ProductController {
    @Resource
    ProductService productService;

    @GetMapping("/productList")
    public ResultUtil selectAll(){
        return productService.getAllProductInfo();
    }
    @PostMapping("/addProduct")
    @ResponseBody
        public ResultUtil addgoods(@RequestBody Product productRecord){
            ResultUtil resultUtil = new ResultUtil();
           if(productRecord==null){
               resultUtil.setCode(201);
               resultUtil.setMessage("插入的商品信息为空");
               return  resultUtil;
           }
            return productService.addProductRecord(productRecord);
        }
    @DeleteMapping("/delProduct")
    public ResultUtil delgoods(@RequestParam(value = "gid") Long gid) {
        ResultUtil resultUtil = new ResultUtil();
        if(gid==null){
           resultUtil.setCode(201);
           resultUtil.setMessage("被删除的商品码不能为空");
           return resultUtil;
       }
        return productService.delProductRecord(gid);
    }

}
