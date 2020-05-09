package com.ledo.market.controller;
import com.ledo.market.entity.InventoryAccounts;
import com.ledo.market.mapper.InventoryAccountsMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//<<<<<<< HEAD
//=======
//import com.ledo.market.entity.InventoryAccounts;
//import com.ledo.market.mapper.InventoryAccountsMapper;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//>>>>>>> 5c07c5b8c2d817cff31ef8dd8ee554fc30650c06
import javax.annotation.Resource;
import java.util.List;

@RestController
public class InventoryAccountsController {
    /*注解存在的位置有将就吗*/
    @Resource
    InventoryAccountsMapper inventoryAccountsMapper;
    @CrossOrigin
    @GetMapping("/home/inventory")
    public List<InventoryAccounts> getAll(){
        return inventoryAccountsMapper.selectAll();
    }
    @CrossOrigin
    @GetMapping("/queryInventory")
    public InventoryAccounts selectByPrimaryKey(@RequestParam(value = "gid") Long gid){
        InventoryAccounts s = inventoryAccountsMapper.selectByPrimaryKey(gid);
        if(s!=null){
            System.out.println("returnItem"+s.getGid());
            return s;
        }
        return null;
    }
}

