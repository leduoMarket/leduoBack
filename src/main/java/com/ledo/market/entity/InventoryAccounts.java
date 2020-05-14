package com.ledo.market.entity;

public class InventoryAccounts {
    private Long gid;
    private String gname;
    private Integer counts;
    private String stockAlert;
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
    public Integer getCounts() {
        return counts;
    }
    public void setCounts(Integer counts) {
        this.counts = counts;
    }
    public String getStockAlert() {
        return stockAlert;
    }
    public void setStockAlert(String stockAlert) {
        this.stockAlert = stockAlert;
    }

}

