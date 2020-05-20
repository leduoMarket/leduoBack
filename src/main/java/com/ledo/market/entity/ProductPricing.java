package com.ledo.market.entity;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductPricing {
    @NonNull
    private Long gid;
    @NonNull
    private String gname;
    @NonNull
    private BigDecimal poldPrice;
    @NotNull
    private BigDecimal pnewPrice;
    private String preason;
    private Date pdate;
    private String phandler;

}