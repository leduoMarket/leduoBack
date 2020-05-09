package com.ledo.market.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FlowPayments {
    private Integer pnumber;
    private Date pdate;
    private String pcategory;
    private String psourceShop;
    private BigDecimal ptradingAmount;
    private BigDecimal premainningAmount;
    public Integer getPnumber() {
        return pnumber;
    }
    public void setPnumber(Integer pnumber) {
        this.pnumber = pnumber;
    }
    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public String getPcategory() {
        return pcategory;
    }

    public void setPcategory(String pcategory) {
        this.pcategory = pcategory;
    }

    public String getPsourceShop() {
        return psourceShop;
    }

    public void setPsourceShop(String psourceShop) {
        this.psourceShop = psourceShop;
    }

    public BigDecimal getPtradingAmount() {
        return ptradingAmount;
    }

    public void setPtradingAmount(BigDecimal ptradingAmount) {
        this.ptradingAmount = ptradingAmount;
    }
    public BigDecimal getPremainningAmount() {
        return premainningAmount;
    }
    public void setPremainningAmount(BigDecimal premainningAmount) {
        this.premainningAmount = premainningAmount;
    }
}