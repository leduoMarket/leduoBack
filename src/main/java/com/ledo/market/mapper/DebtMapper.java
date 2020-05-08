package com.ledo.market.mapper;

import com.ledo.market.entity.Debt;
import java.util.List;

public interface DebtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table debt
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int deleteByPrimaryKey(String dnumber);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table debt
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int insert(Debt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table debt
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    Debt selectByPrimaryKey(String dnumber);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table debt
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    List<Debt> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table debt
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int updateByPrimaryKey(Debt record);
    String delete(String dnumber);
}