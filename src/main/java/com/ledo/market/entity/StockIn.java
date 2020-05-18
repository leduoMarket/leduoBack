package com.ledo.market.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author 11153
 */
@Data
public class StockIn {
    private String inumber;
    private Long gid;
    private String vname;
    private Date idate;
    private BigDecimal iprice;
    private BigDecimal ipayment;
    private Integer icount;
}