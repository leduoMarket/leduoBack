package com.ledo.market.mapper;

import com.ledo.market.entity.StockIn;
import org.apache.ibatis.annotations.Mapper;


import java.util.Date;
import java.util.List;

@Mapper
public interface StockInMapper{
    String delete(String inumber);
    int insert(StockIn record);
    StockIn selectByPrimaryKey(String inumber);
    List<StockIn> selectAll();
    int updateByPrimaryKey(StockIn record);
    List<Date> putstockindate();
    StockIn selectByInumber(String inumber);
    List<Integer> putstockinsum();
}