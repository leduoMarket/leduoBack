package com.ledo.market.controller;
import com.ledo.market.entity.InventoryAccounts;
import com.ledo.market.mapper.InventoryAccountsMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 * 对库存账进行查询支持
 */
@RestController
@RequestMapping("/staff")
public class InventoryAccountsController {
    @Resource
    InventoryAccountsMapper inventoryAccountsMapper;
    @Resource
    RedisUtil redisUtil;
    @GetMapping("/incentoryAccounts")
    public ResultUtil getAll(){
        ResultUtil resultUtil = new ResultUtil();
        List <InventoryAccounts> inventoryAccountsList = (List<InventoryAccounts>) redisUtil.get("inventoryAccountsList");
        if(inventoryAccountsList==null){
            inventoryAccountsList = (List<InventoryAccounts>) redisUtil.get("inventoryAccountsList");
            if(inventoryAccountsList==null){
                synchronized (this){
                    inventoryAccountsList = inventoryAccountsMapper.selectAll();
                    redisUtil.set("inventoryAccountsList",inventoryAccountsList);
                    redisUtil.expire("inventoryAccountsList",10);
                }
            }
        }
        if(inventoryAccountsList==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("没有查询到有关库存的消息");
            return resultUtil;
        }
        resultUtil.setCode(200);
       resultUtil.setData(inventoryAccountsList);
       return resultUtil;
    }

}

