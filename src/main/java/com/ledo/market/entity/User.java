package com.ledo.market.entity;

import java.util.Objects;

/**
 * @author 王梦琼
 */
public class User {
    private String userName;
    private String password;
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
