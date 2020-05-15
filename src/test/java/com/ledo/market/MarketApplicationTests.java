package com.ledo.market;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Slf4j
class MarketApplicationTests {
//    @Autowired
//    DataSource dataSource;
//    @Test
//    void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
//        //从数据源获取连接
//        Connection connection;
//        connection = dataSource.getConnection();
//        System.out.println(connection);
//        connection.close();
//    }

//    private final Logger logger =  LoggerFactory.getLogger(MarketApplicationTests.class);
    @Test
    public void contextLoads() {
//        logger.info("- 数据库日志info");
//        logger.error("- 数据库日志error");
//        logger.info("一条不带‘-’的日志，看会不会记录如数据库");
        log.info("- 数据库日志info");
        log.error("- 数据库日志error");
        log.info("一条不带‘-’的日志，看会不会记录如数据库");
    }
}
