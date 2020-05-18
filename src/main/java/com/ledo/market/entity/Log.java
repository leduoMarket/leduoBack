package com.ledo.market.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Log {
    private Long id;
    private String message;
    private String levelString;
    private Date createdTime;
    private String loggerName;
    private String handler;
}