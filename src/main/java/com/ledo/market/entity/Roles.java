package com.ledo.market.entity;

import lombok.Data;

@Data
public class Roles {
    private Integer rid;
    private String role;
    private String permission;
    public Integer getRid() {
        return rid;
    }
}