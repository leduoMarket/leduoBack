package com.ledo.market.mapper;

import com.ledo.market.entity.ProductReturn;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ProductReturnMapper {
    int insert(ProductReturn record);
    List<ProductReturn> selectAll();
    Integer delete(Long gid);
    ProductReturn selectByPrimaryKey(Long gid);
}