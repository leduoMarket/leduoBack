package com.ledo.market.mapper;

import com.ledo.market.entity.InventoryAccounts;
import java.util.List;

public interface InventoryAccountsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inventory_accounts
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    int insert(InventoryAccounts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inventory_accounts
     *
     * @mbg.generated Wed May 06 22:38:32 CST 2020
     */
    List<InventoryAccounts> selectAll();
}