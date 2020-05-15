package com.ledo.market.result;

/**
 * @author 王梦琼
 * 作用：在Http的post请求中将200或400等状态码封装成对象返回
 */
public class StatusCodeResult {
    private int code;
    public StatusCodeResult(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
