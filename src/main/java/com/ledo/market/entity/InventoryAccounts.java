package com.ledo.market.entity;

import lombok.Data;

/**
 * 库存账实体类
 * */
@Data
public class InventoryAccounts {
    private Long gid;
    private String gname;
    private Integer counts;
    private String stockAlert;
}

