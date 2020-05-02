package com.ledo.market.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;

/**
 * @author 王梦琼
 */
public class Employee {
    private Integer eid;
    private String ename;
    private String ephone;
    private String erole;
    private BigDecimal esalary;
    public Integer getEid() {
        return eid;
    }
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getEphone() {
        return ephone;
    }
    public void setEphone(String ephone) {
        this.ephone = ephone;
    }

    public String getErole() {
        return erole;
    }

    public void setErole(String erole) {
        this.erole = erole;
    }

    public BigDecimal getEsalary() {
        return esalary;
    }
    public void setEsalary(BigDecimal esalary) {
        this.esalary = esalary;
    }
}