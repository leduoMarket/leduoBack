package com.ledo.market.mapper;

import com.ledo.market.entity.Vender;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VenderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vender
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int deleteByPrimaryKey(Integer vid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vender
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int insert(Vender record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vender
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    Vender selectByPrimaryKey(Integer vid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vender
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    List<Vender> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vender
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    int updateByPrimaryKey(Vender record);
}