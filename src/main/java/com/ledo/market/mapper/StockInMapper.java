package com.ledo.market.mapper;

import com.ledo.market.entity.StockIn;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Mapper
public interface StockInMapper{
    String delete(String inumber);
    int insert(StockIn record) throws SQLException;
    StockIn selectByPrimaryKey(String inumber);
    List<StockIn> selectAll();
    int updateByPrimaryKey(StockIn record);

    StockIn selectByInumber(String inumber);
}