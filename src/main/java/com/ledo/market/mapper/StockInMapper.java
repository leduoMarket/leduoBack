package com.ledo.market.mapper;

import com.ledo.market.entity.StockIn;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface StockInMapper{
    String delete(String inumber);
    int insert(StockIn record) throws SQLException;
    StockIn selectByPrimaryKey(String inumber);
    List<StockIn> selectAll();
    int updateByPrimaryKey(StockIn record);

    StockIn selectByInumber(String inumber);
    List<Map> putstockinsum();
}