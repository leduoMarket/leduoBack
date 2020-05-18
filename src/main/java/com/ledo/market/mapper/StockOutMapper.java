package com.ledo.market.mapper;

import com.ledo.market.entity.StockOut;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface StockOutMapper {
    int deleteByPrimaryKey(String onumber);

    int insert(StockOut record)  throws DuplicateKeyException;

    StockOut selectByPrimaryKey(String onumber);
    List<StockOut> selectAll();
    int updateByPrimaryKey(StockOut record);
    String delete(String onumber);
    List<Date> putstockoutdate();
    List<Map> putstockoutsum();
}