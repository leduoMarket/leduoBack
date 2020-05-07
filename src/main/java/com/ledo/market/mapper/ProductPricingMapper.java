package com.ledo.market.mapper;

import com.ledo.market.entity.ProductPricing;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductPricingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_pricing
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int insert(ProductPricing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_pricing
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    List<ProductPricing> selectAll();
    ProductPricing selectByPrimaryKey(Long gid);
    Integer delete(int gid);
}