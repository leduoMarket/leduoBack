package com.ledo.market.entity;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class Product {
    private Long gid;
    private String gname;
    private String categories;
    private String address;
    private BigDecimal chargeUnit;
    private Date gdate;
}