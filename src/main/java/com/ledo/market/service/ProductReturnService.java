package com.ledo.market.service;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.ledo.market.entity.ProductReturn;
import com.ledo.market.mapper.ProductReturnMapper;
import com.ledo.market.mapper.StockOutMapper;
import com.ledo.market.utils.RedisUtil;
import com.ledo.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ProductReturnService {
    @Resource
    RedisUtil redisUtil;
    @Resource
    ProductReturnMapper productReturnMapper;

    @Resource
    StockOutMapper stockOutMapper;
    /**
     * 查询所有的退货单记录
     * */
    public ResultUtil getAll(){
        ResultUtil resultUtil = new ResultUtil();
        List<ProductReturn> productReturnList = (List<ProductReturn>) redisUtil.get("productReturnList");
        if(productReturnList==null){
            productReturnList = (List<ProductReturn>) redisUtil.get("productReturnList");
            if(productReturnList==null){
                productReturnList=productReturnMapper.selectAll();
                redisUtil.set("productReturnList",productReturnList);
            }
        }
        if(productReturnList==null){
            resultUtil.setCode(201);
            resultUtil.setMessage("没有查询到退货单的记录");
            return resultUtil;
        }
        resultUtil.setCode(200);
        resultUtil.setData(productReturnList);
        return resultUtil;
    }

    /**
     * 删除退货单记录
     * */
    public ResultUtil delRecord(Long gid){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("productReturnList");
        productReturnMapper.delete(gid);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
           System.out.println("删除退货单记录时的延时错误");
        }
        redisUtil.del("productReturnList");
        resultUtil.setCode(200);
        resultUtil.setMessage("删除记录成功");
        return resultUtil;
    }
    /**
     * 新增退货单记录，判断该商品是否存在退货记录
     * 判断退货数量是否在合法范围内
     * */
    public ResultUtil addRecord(ProductReturn productReturnRecord){
        ResultUtil resultUtil = new ResultUtil();
        redisUtil.del("productReturnList");
        //判断请求退货的商品是否已经出库
        long hasStockOut = productReturnMapper.ifOutExistsGid(productReturnRecord.getGid());
        if(hasStockOut==0){
            resultUtil.setCode(201);
            resultUtil.setMessage("退货单的商品在出库单中未找到记录");
            return resultUtil;
        }

        /**
         * 判断该商品的退货数量是否小于出库单的退货数量
         * */
        int numberOfReturn = productReturnMapper.getCounts(productReturnRecord.getGid());
        int numberOfStockOut = stockOutMapper.getStockOutCount(productReturnRecord.getGid());
        if((numberOfReturn+productReturnRecord.getRcount())>numberOfStockOut){
            resultUtil.setCode(201);
            resultUtil.setMessage("请求的退货数量不能大于所有的出库量");
            return resultUtil;
        }
        //同一个商品gid允许多次退货，不存在duplicated异常
        productReturnMapper.insert(productReturnRecord);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("增加退货单记录时的延时错误");
        }
        redisUtil.del("productReturnList");
       resultUtil.setCode(200);
       resultUtil.setMessage("退货成功");
       return resultUtil;
    }
}
