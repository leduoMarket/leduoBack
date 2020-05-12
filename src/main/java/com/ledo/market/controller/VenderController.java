package com.ledo.market.controller;


import com.ledo.market.entity.Vender;
import com.ledo.market.mapper.VenderMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class VenderController {
    @Resource
    VenderMapper vendermapper;
    @CrossOrigin
    @GetMapping("/Vender")

    public List<Vender> selectAll(){
        return vendermapper.selectAll();
    }

    @CrossOrigin
    @PostMapping("/addVender")
    @ResponseBody
    public StatusCodeResult addvender(@RequestBody Vender reqven){
        System.out.println(reqven.getVid());
        System.out.println(reqven.getVname());
        System.out.println(vendermapper.insert(reqven));
        return new StatusCodeResult(200);
    }
    @CrossOrigin
    @DeleteMapping("/delVender")
    public StatusCodeResult delemp(@RequestParam(value = "venderId") String venderId) {
        System.out.println("empID:" + venderId);
        Long goodsIds;
        goodsIds = Long.parseLong(venderId);
        System.out.println(vendermapper.delete(goodsIds));
        return new StatusCodeResult(200);
    }


}
