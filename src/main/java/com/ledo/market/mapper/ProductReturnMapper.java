package com.ledo.market.mapper;

import com.ledo.market.entity.ProductReturn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ProductReturnMapper {
    int insert(ProductReturn record);
    List<ProductReturn> selectAll();
    Integer delete(Long gid);
    ProductReturn selectByPrimaryKey(Long gid);

    /**
     * 判断要退货的商品是否已经出过库
     * */
    Long ifOutExistsGid(Long gid);
    /**
     * 用于判断退货总数是否大于出库单该商品号的总数
     * */
    int getCounts(Long gid);
}