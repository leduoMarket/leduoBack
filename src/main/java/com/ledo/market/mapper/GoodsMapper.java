package com.ledo.market.mapper;

import com.ledo.market.entity.Goods;
import java.util.List;

public interface GoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int insert(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    List<Goods> selectAll();
}