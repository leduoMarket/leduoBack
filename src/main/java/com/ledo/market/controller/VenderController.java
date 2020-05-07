package com.ledo.market.controller;


import com.ledo.market.entity.Vender;
import com.ledo.market.mapper.VenderMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class VenderController {
    @Resource
    VenderMapper vendermapper;
    @CrossOrigin
    @GetMapping("/vender")
    public List<Vender> selectAll(){
        return vendermapper.selectAll();
    }

}
