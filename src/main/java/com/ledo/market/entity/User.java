package com.ledo.market.entity;

import java.math.BigDecimal;

/**
 * @author 王梦琼
 */
public class User {
    private String uid;
    private String userName;
    private String password;
    private String phone;
    private String role;
    private Integer satatus;
//    private String pwd1;
//    private String pwd2;
//
//    public String getPwd2() {
//        return pwd2;
//    }
//
//    public void setPwd2(String pwd2) {
//        this.pwd2 = pwd2;
//    }
//    public String getPwd1() {
//        return pwd1;
//    }
//
//    public void setPwd1(String pwd1) {
//        this.pwd1 = pwd1;
//    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSatatus() {
        return satatus;
    }

    public void setSatatus(Integer satatus) {
        this.satatus = satatus;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /*
    * 重写equals方法判断两个对象是否相等
    * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        User user = (User) o;
        return userName.equals(user.userName) &&
                password.equals(user.password);
    }
}
