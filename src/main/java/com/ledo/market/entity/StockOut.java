package com.ledo.market.entity;

import java.math.BigDecimal;
import java.util.Date;

public class StockOut {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stock_out.onumber
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private String onumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stock_out.gid
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private Long gid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stock_out.vname
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private String vname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stock_out.odate
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private Date odate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stock_out.oprice
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private BigDecimal oprice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stock_out.opayment
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private BigDecimal opayment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stock_out.ocount
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private Integer ocount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stock_out.onumber
     *
     * @return the value of stock_out.onumber
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public String getOnumber() {
        return onumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stock_out.onumber
     *
     * @param onumber the value for stock_out.onumber
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setOnumber(String onumber) {
        this.onumber = onumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stock_out.gid
     *
     * @return the value of stock_out.gid
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public Long getGid() {
        return gid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stock_out.gid
     *
     * @param gid the value for stock_out.gid
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setGid(Long gid) {
        this.gid = gid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stock_out.vname
     *
     * @return the value of stock_out.vname
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public String getVname() {
        return vname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stock_out.vname
     *
     * @param vname the value for stock_out.vname
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setVname(String vname) {
        this.vname = vname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stock_out.odate
     *
     * @return the value of stock_out.odate
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public Date getOdate() {
        return odate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stock_out.odate
     *
     * @param odate the value for stock_out.odate
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setOdate(Date odate) {
        this.odate = odate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stock_out.oprice
     *
     * @return the value of stock_out.oprice
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public BigDecimal getOprice() {
        return oprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stock_out.oprice
     *
     * @param oprice the value for stock_out.oprice
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setOprice(BigDecimal oprice) {
        this.oprice = oprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stock_out.opayment
     *
     * @return the value of stock_out.opayment
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public BigDecimal getOpayment() {
        return opayment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stock_out.opayment
     *
     * @param opayment the value for stock_out.opayment
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setOpayment(BigDecimal opayment) {
        this.opayment = opayment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stock_out.ocount
     *
     * @return the value of stock_out.ocount
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public Integer getOcount() {
        return ocount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stock_out.ocount
     *
     * @param ocount the value for stock_out.ocount
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setOcount(Integer ocount) {
        this.ocount = ocount;
    }
}