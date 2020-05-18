package com.ledo.market.mapper;

import com.ledo.market.entity.Vender;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Mapper
public interface VenderMapper {
    int deleteByPrimaryKey(Integer vid);
    int insert(Vender record) throws DuplicateKeyException;
    Vender selectByPrimaryKey(Integer vid);
    List<Vender> selectAll();
    int updateByPrimaryKey(Vender record);
    String delete(Integer vid);
}