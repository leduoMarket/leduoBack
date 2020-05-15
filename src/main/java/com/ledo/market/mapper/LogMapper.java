package com.ledo.market.mapper;

import com.ledo.market.entity.Log;
import java.util.List;

public interface LogMapper {
    List<Log> selectAll();
    /**
     * 查询日志文件中的前200条数据
     * */
    List<Log> selectSome();
}