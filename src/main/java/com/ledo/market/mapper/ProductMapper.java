package com.ledo.market.mapper;

import com.ledo.market.entity.Product;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

public interface ProductMapper {
    int insert(Product record) throws DuplicateKeyException;
    List<Product> selectAll();
    Product selectByPrimaryKey(Long gid);
    Integer delete(Long gid);
    String getProductNameByPnumber(Long gid);
}