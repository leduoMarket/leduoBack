package com.ledo.market.mapper;

import com.ledo.market.entity.StockIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockInMapper{
    int deleteByPrimaryKey(String inumber);
    int insert(StockIn record);
    StockIn selectByInumber(String inumber);
    List<StockIn> selectAll();
    int updateByPrimaryKey(StockIn record);
}