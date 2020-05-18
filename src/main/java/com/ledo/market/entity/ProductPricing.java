package com.ledo.market.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductPricing {

    private Long gid;

    private String gname;

    private BigDecimal poldPrice;

    private BigDecimal pnewPrice;

    private String preason;

    private Date pdate;

    private String phandler;

}