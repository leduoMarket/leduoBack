package com.ledo.market.entity;
import lombok.Data;

import java.sql.Date;

@Data
public class Product {
    private Long gid;
    private String gname;
    private String categories;
    private String address;
    private String chargeUnit;
    private Date gdate;
}