package com.ledo.market.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Vender {
    private Integer vid;
    private String vname;
    private String vaddress;
    private String vphone;
    private String vemail;
    private String vfax;
    private Integer vcredit;
    private BigDecimal vsettleAccount;
}