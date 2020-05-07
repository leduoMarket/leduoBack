package com.ledo.market.entity;

public class InventoryAccounts {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inventory_accounts.gid
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    private Long gid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inventory_accounts.gname
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    private String gname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inventory_accounts.counts
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    private Integer counts;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inventory_accounts.stock_alert
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    private String stockAlert;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inventory_accounts.gid
     *
     * @return the value of inventory_accounts.gid
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    public Long getGid() {
        return gid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inventory_accounts.gid
     *
     * @param gid the value for inventory_accounts.gid
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    public void setGid(Long gid) {
        this.gid = gid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inventory_accounts.gname
     *
     * @return the value of inventory_accounts.gname
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    public String getGname() {
        return gname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inventory_accounts.gname
     *
     * @param gname the value for inventory_accounts.gname
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    public void setGname(String gname) {
        this.gname = gname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inventory_accounts.counts
     *
     * @return the value of inventory_accounts.counts
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    public Integer getCounts() {
        return counts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inventory_accounts.counts
     *
     * @param counts the value for inventory_accounts.counts
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inventory_accounts.stock_alert
     *
     * @return the value of inventory_accounts.stock_alert
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    public String getStockAlert() {
        return stockAlert;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inventory_accounts.stock_alert
     *
     * @param stockAlert the value for inventory_accounts.stock_alert
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    public void setStockAlert(String stockAlert) {
        this.stockAlert = stockAlert;
    }
}