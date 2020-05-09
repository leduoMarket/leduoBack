package com.ledo.market.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Goods {
    private Long gid;
    private String gname;
    private String categories;
    private String address;
    private BigDecimal charge_unit;
    private Date gdate;

    public Long getGid() {
        return gid;
    }
    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }
    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCharge_unit() {
        return charge_unit;
    }
    public void setCharge_unit(BigDecimal charge_unit) {
        this.charge_unit = charge_unit;
    }

    public Date getGdate() {
        return gdate;
    }
    public void setGdate(Date gdate) {
        this.gdate = gdate;
    }

}