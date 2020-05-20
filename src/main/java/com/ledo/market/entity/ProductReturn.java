package com.ledo.market.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductReturn {
    private Long gid;
    private Date rdate;
    private String rreason;
    private Integer rcount;
}