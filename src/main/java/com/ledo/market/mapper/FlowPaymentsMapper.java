package com.ledo.market.mapper;

import com.ledo.market.entity.FlowPayments;
import java.util.List;

public interface FlowPaymentsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_payments
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int deleteByPrimaryKey(Integer pnumber);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_payments
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int insert(FlowPayments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_payments
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    FlowPayments selectByPrimaryKey(Integer pnumber);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_payments
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    List<FlowPayments> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_payments
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int updateByPrimaryKey(FlowPayments record);
}