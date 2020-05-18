package com.ledo.market.entity;

import lombok.Data;

/**
 * @author 王梦琼
 */
@Data
public class User {
    private String uid;
    private String userName;
    private String password;
    private String phone;
    private String role;
    private Integer status;

    /**
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
