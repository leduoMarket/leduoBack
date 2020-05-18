package com.ledo.market.mapper;

import com.ledo.market.entity.FlowPayments;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

public interface FlowPaymentsMapper {

    int deleteByPrimaryKey(Integer pnumber);

    int insert(FlowPayments record) throws DuplicateKeyException;

    FlowPayments selectByPrimaryKey(Integer pnumber);

    List<FlowPayments> selectAll();

    int updateByPrimaryKey(FlowPayments record);
    Integer delete(Integer pnumber);
}