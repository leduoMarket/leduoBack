package com.ledo.market.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StockOut {
    private String onumber;
    private Long gid;
    private String vname;
    private Date odate;
    private BigDecimal oprice;
    private BigDecimal opayment;
}