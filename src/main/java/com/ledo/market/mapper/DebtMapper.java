package com.ledo.market.mapper;

import com.ledo.market.entity.Debt;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

public interface DebtMapper {
    int deleteByPrimaryKey(String dnumber);
    int insert(Debt record) throws DuplicateKeyException;
    Debt selectByPrimaryKey(String dnumber);
    List<Debt> selectAll();
    int updateByPrimaryKey(Debt record);
    String delete(String dnumber);
}