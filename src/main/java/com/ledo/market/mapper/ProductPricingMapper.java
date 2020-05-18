package com.ledo.market.mapper;

import com.ledo.market.entity.ProductPricing;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Mapper
public interface ProductPricingMapper {
    int insert(ProductPricing record) throws DuplicateKeyException;
    List<ProductPricing> selectAll();
    ProductPricing selectByPrimaryKey(Long gid);
    Integer delete(Long gid);
}