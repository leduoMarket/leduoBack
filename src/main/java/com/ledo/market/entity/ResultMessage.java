package com.ledo.market.entity;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author 王梦琼
 */
public class ResultMessage {
    private int code;
    private Object token;

    public Object getToken() {
        return token;
    }

    public ResultMessage(int code, Object token) {
        this.code = code;
        this.token = token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
