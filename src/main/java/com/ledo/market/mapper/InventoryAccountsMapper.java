package com.ledo.market.mapper;

import com.ledo.market.entity.InventoryAccounts;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface InventoryAccountsMapper {
    int insert(InventoryAccounts record) throws DuplicateKeyException;
    List<InventoryAccounts> selectAll();
    InventoryAccounts selectByPrimaryKey(Long gid);
}