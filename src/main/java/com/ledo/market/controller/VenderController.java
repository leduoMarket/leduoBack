package com.ledo.market.controller;


import com.ledo.market.entity.Vender;
import com.ledo.market.mapper.VenderMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
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


}
