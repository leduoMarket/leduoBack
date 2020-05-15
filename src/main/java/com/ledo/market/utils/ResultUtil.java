package com.ledo.market.utils;

import org.springframework.stereotype.Component;

/**
 * @author 王梦琼
 * 返回消息的工具类
 */
@Component
public class ResultUtil {
    /**
     *  200为成功，其他为异常
     */
    public int code;
    /**
     * 返回成功或失败消息
     * */
    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 返回携带的消息
     * */
    public Object data;
}
