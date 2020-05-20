package com.ledo.market.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Debt {
    private String dnumber;
    private String vname;
    private Long gid;
    private Date ddate;
    private BigDecimal ddebt;
}