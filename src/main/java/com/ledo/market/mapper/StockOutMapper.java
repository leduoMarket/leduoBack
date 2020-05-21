package com.ledo.market.mapper;

import com.ledo.market.entity.StockOut;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface StockOutMapper {
    int deleteByPrimaryKey(String onumber);
    int insert(StockOut record)  throws Exception;
    StockOut selectByPrimaryKey(String onumber);
    List<StockOut> selectAll();
    String delete(String onumber);
    List<Date> putstockoutdate();
    List<Map> putstockoutsum();
    /**
     * 计算指定商品号的商品总的出库记录，用于判断出库单中是否此商品是否不合理的退货
     * */
    int getStockOutCount(Long gid);
}