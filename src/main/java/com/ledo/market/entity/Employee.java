package com.ledo.market.entity;

import java.math.BigDecimal;

public class Employee {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.eid
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private Integer eid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.ename
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private String ename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.ephone
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private String ephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.erole
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private String erole;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.esalary
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    private BigDecimal esalary;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.eid
     *
     * @return the value of employee.eid
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public Integer getEid() {
        return eid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.eid
     *
     * @param eid the value for employee.eid
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.ename
     *
     * @return the value of employee.ename
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public String getEname() {
        return ename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.ename
     *
     * @param ename the value for employee.ename
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.ephone
     *
     * @return the value of employee.ephone
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public String getEphone() {
        return ephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.ephone
     *
     * @param ephone the value for employee.ephone
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setEphone(String ephone) {
        this.ephone = ephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.erole
     *
     * @return the value of employee.erole
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public String getErole() {
        return erole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.erole
     *
     * @param erole the value for employee.erole
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setErole(String erole) {
        this.erole = erole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.esalary
     *
     * @return the value of employee.esalary
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public BigDecimal getEsalary() {
        return esalary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.esalary
     *
     * @param esalary the value for employee.esalary
     *
     * @mbg.generated Sat May 02 14:02:10 CST 2020
     */
    public void setEsalary(BigDecimal esalary) {
        this.esalary = esalary;
    }
}