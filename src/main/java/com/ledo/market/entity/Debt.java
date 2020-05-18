package com.ledo.market.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Debt {
    private String dnumber;
    private Integer vid;
    private String vname;
    private Long gid;
    private String gname;
    private Date ddate;
    private BigDecimal ddebt;
}