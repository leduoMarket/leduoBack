package com.ledo.market.entity;

import java.util.Date;

public class Log {
    private Long id;
    private String message;
    private String levelString;
    private Date createdTime;
    private String loggerName;
    private String handler;
    public String getHandler() {
        return handler;
    }
    public void setHandler(String handler) {
        this.handler = handler;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getLevelString() {
        return levelString;
    }
    public void setLevelString(String levelString) {
        this.levelString = levelString;
    }
    public Date getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    public String getLoggerName() {
        return loggerName;
    }
    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}