package com.ledo.market.service;

import com.ledo.market.entity.ProductPricing;
import com.ledo.market.mapper.ProductMapper;
import com.ledo.market.mapper.ProductPricingMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ProductPricingService {
    @Resource
    ProductMapper productMapper;
    @Resource
    RedisUtil redisUtil;
   @Resource
    ProductPricingMapper productPricingMapper;
   /**
    * 查看所有的商品定价的信息
    * */
   public ResultUtil getAllProductPricingInfo(){
       ResultUtil resultUtil = new ResultUtil();
       List<ProductPricing> productPricingInfo = (List<ProductPricing>) redisUtil.get("productPricingInfo");
       if(productPricingInfo==null){
           productPricingInfo = (List<ProductPricing>) redisUtil.get("productPricingInfo");
           if(productPricingInfo==null){
               synchronized (this){
                   productPricingInfo=productPricingMapper.selectAll();
                   redisUtil.set("productPricingInfo",productPricingInfo);
               }
           }
       }
       if(productPricingInfo==null){
           resultUtil.setCode(201);
           resultUtil.setMessage("没有查询到关于商品定价的信息");
           return resultUtil;
       }
       resultUtil.setCode(200);
       log.info("-查看商品定价的信息");
       resultUtil.setData(productPricingInfo);
       return resultUtil;
   }
   /**
    * 增加一行商品定价信息
    * */
   public ResultUtil addProductPricingRecord(ProductPricing productPricingRecord){
       ResultUtil resultUtil = new ResultUtil();
       String productName = productMapper.getProductNameByPnumber(productPricingRecord.getGid());
       if(productName==null){
           resultUtil.setCode(201);
           resultUtil.setMessage("定价商品在商品表中未找到，请先插入");
           return resultUtil;
       }
       if(!productPricingRecord.getGname().equals(productName)){
           resultUtil.setCode(201);
           resultUtil.setMessage("定价商品名与商品编号不一致，商品名应当为:"+productName);
           return resultUtil;
       }
       redisUtil.del("productPricingInfo");
       int influenceLine = 0;
       influenceLine = productPricingMapper.insert(productPricingRecord);
       if(influenceLine==0){
           resultUtil.setCode(200);
           resultUtil.setMessage("增加记录失败");
           log.info("-商品号为："+productPricingRecord.getGid()+"的商品增加失败");
           return resultUtil;
       }
       try {
           Thread.sleep(500);
       } catch (InterruptedException e) {
           System.out.println("增减商品信息时缓存延时失败");
       }
       redisUtil.del("productPricingInfo");
       resultUtil.setCode(200);
       resultUtil.setMessage("增加记录成功");
       log.info("-成功增加商品号为："+productPricingRecord.getGid()+"的记录");
       return resultUtil;
   }
   /**
    * 删除一行商品定价信息，采用延时双删的方法保证数据库与缓存的信息一致
    * */
   public ResultUtil delProductPricingInfo(Long gid){
       ResultUtil resultUtil = new ResultUtil();
       redisUtil.del("productPricingInfo");
       productPricingMapper.delete(gid);
       try {
           Thread.sleep(500);
       } catch (InterruptedException e) {
           System.out.println("删除商品信息时缓存延时失败");
       }
       redisUtil.del("productPricingInfo");
       resultUtil.setCode(200);
       log.warn("-删除商品号为:"+gid+"的记录");
       resultUtil.setMessage("删除成功");
       return resultUtil;
   }

}
