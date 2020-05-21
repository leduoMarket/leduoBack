package com.ledo.market.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付流水账实体类
 * */
@Data
public class FlowPayments {
    private String pnumber;
    private Date pdate;
    private String pcategory;
    private String psourceShop;
    private BigDecimal ptradingAmount;
    private BigDecimal premainningAmount;
}