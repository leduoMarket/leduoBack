package com.ledo.market.entity;
import java.sql.Date;

public class Goods {
    private Long gid;
    private String gname;
    private String categories;
    private String address;
    private String chargeUnit;
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

    public String getChargeUnit() {
        return chargeUnit;
    }
    public void setChargeUnit(String chargeUnit) {
        this.chargeUnit = chargeUnit;
    }

    public Date getGdate() {
        return gdate;
    }
    public void setGdate(Date gdate) {
        this.gdate = gdate;
    }

}