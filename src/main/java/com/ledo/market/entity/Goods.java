package com.ledo.market.entity;

import java.math.BigDecimal;

public class Goods {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.gid
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private Long gid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.gname
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private String gname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.gcategory
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private String gcategory;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.gprice
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private BigDecimal gprice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.gcount
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private Integer gcount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.gid
     *
     * @return the value of goods.gid
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public Long getGid() {
        return gid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.gid
     *
     * @param gid the value for goods.gid
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setGid(Long gid) {
        this.gid = gid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.gname
     *
     * @return the value of goods.gname
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public String getGname() {
        return gname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.gname
     *
     * @param gname the value for goods.gname
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setGname(String gname) {
        this.gname = gname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.gcategory
     *
     * @return the value of goods.gcategory
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public String getGcategory() {
        return gcategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.gcategory
     *
     * @param gcategory the value for goods.gcategory
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setGcategory(String gcategory) {
        this.gcategory = gcategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.gprice
     *
     * @return the value of goods.gprice
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public BigDecimal getGprice() {
        return gprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.gprice
     *
     * @param gprice the value for goods.gprice
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setGprice(BigDecimal gprice) {
        this.gprice = gprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.gcount
     *
     * @return the value of goods.gcount
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public Integer getGcount() {
        return gcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.gcount
     *
     * @param gcount the value for goods.gcount
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setGcount(Integer gcount) {
        this.gcount = gcount;
    }
}