package com.ledo.market.controller;

import com.ledo.market.entity.ProductPricing;
import com.ledo.market.entity.ProductReturn;
import com.ledo.market.mapper.ProductReturnMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodsReturnController {
    @Resource
    ProductReturnMapper productReturnMapper;
    @CrossOrigin
    @GetMapping("/goodsReturn")
    public List<ProductReturn> selectAll(){
        return productReturnMapper.selectAll();
    }

    @CrossOrigin
    @PostMapping("/addgoodsReturn")
    @ResponseBody
    public StatusCodeResult addpreturn(@RequestBody ProductReturn reqreturn){
        System.out.println(reqreturn.getGid());
        System.out.println(productReturnMapper.insert(reqreturn));
        return new StatusCodeResult(200);
    }

    @CrossOrigin
    @DeleteMapping("/delgoodsReturn")
    public StatusCodeResult delemp(@RequestParam(value = "goodsId") String goodsId) {
        System.out.println("returnID:" + goodsId);
        int goodsIds;
        goodsIds = Integer.parseInt(goodsId);
        System.out.println(productReturnMapper.delete(goodsIds));
        return new StatusCodeResult(200);
    }

    @CrossOrigin
    @GetMapping("/querygoodsReturn")
    public ProductReturn selectByPrimaryKey(@RequestParam(value = "gid") Long gid){
        ProductReturn s = productReturnMapper.selectByPrimaryKey(gid);
        if(s!=null){
            System.out.println("returnItem"+s.getGid());
            return s;
        }
        return null;
    }
}
