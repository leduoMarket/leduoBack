package com.ledo.market.entity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author 11153
 */
public class StockIn {
    private String inumber;
    private Long gid;
    private String vname;
    private Date idate;
    private BigDecimal iprice;
    private BigDecimal ipayment;
    private Integer icount;

    public String getInumber() {
        return inumber;
    }
    public void setInumber(String inumber) {
        this.inumber = inumber;
    }
    public Long getGid() {
        return gid;
    }
    public void setGid(Long gid) {
        this.gid = gid;
    }
    public String getVname() {
        return vname;
    }
    public void setVname(String vname) {
        this.vname = vname;
    }
    public Date getIdate() {
        return idate;
    }
    public void setIdate(Date idate) {
        this.idate = idate;
    }
    public BigDecimal getIprice() {
        return iprice;
    }
    public void setIprice(BigDecimal iprice) {
        this.iprice = iprice;
    }
    public BigDecimal getIpayment() {
        return ipayment;
    }
    public void setIpayment(BigDecimal ipayment) {
        this.ipayment = ipayment;
    }
    public Integer getIcount() {
        return icount;
    }
    public void setIcount(Integer icount) {
        this.icount = icount;
    }

}