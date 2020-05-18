package com.ledo.market.controller;

import com.ledo.market.entity.Goods;
import com.ledo.market.mapper.ProductMapper;
import com.ledo.market.result.StatusCodeResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**商品表
 * @author lenovo**/
@RestController
@RequestMapping("/staff")
public class ProductController {
    @Resource
    ProductMapper goodsmapper;

    @GetMapping("/goods")
    public List<Goods> selectAll(){
        return goodsmapper.selectAll();
    }

    @GetMapping("/queryGoods")
    public Goods selectByPrimaryKey(@RequestParam(value="gid") Long gid){
            Goods s = goodsmapper.selectByPrimaryKey(gid);
            if(s!=null){
                System.out.println("returnItem"+s.getGid());
                return s;
            }
            return null;
        }
    @PostMapping("/Goods")
    @ResponseBody
        public StatusCodeResult addgoods(@RequestBody Goods reqgood){
            System.out.print(reqgood.getGname());
            System.out.println(goodsmapper.insert(reqgood));
            return new StatusCodeResult(200);
        }

    @DeleteMapping("/delGoods")
    public StatusCodeResult delgoods(@RequestParam(value = "GoodsId") String GoodsId) {
        System.out.println("empID:" + GoodsId);
        Long goodsIds;
        goodsIds = Long.parseLong(GoodsId);
        System.out.println(goodsmapper.delete(goodsIds));
        return new StatusCodeResult(200);
    }

}
