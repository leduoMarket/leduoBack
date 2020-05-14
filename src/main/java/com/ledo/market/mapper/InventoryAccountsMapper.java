package com.ledo.market.mapper;

import com.ledo.market.entity.InventoryAccounts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface InventoryAccountsMapper {
    int insert(InventoryAccounts record);
    List<InventoryAccounts> selectAll();
    InventoryAccounts selectByPrimaryKey(Long gid);
}