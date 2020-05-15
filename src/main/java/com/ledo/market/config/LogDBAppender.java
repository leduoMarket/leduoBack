package com.ledo.market.config;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.db.DBAppenderBase;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author 王梦琼
 * 自定义存入数据库的文件的格式配置文件
 */
@Configuration
public class LogDBAppender extends DBAppenderBase<ILoggingEvent> {
    protected static final Method GET_GENERATED_KEYS_METHOD;
    /**
     * 插入的sql语句
     */
    protected String insertSQL;
    /**
     * message 日志内容
     * */
    static final int MESSAGE = 1;
    /**
     * levelString,级别
     * */
    static final int LEVEL_STRING = 2;
    // created_time 时间
    /**
     * 创建时间CREATE_TIME
     * */
    static final int CREATE_TIME = 3;
    /**
     * logger_name 全类名
     * */
    static final int LOGGER_NAME = 4;

    /**
     * 以-开头则记录到数据库文件中
     * */
    static final String StartWithPaternThenSaveToDB = "-";

    static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();

    static {
        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
        Method getGeneratedKeysMethod;
        try {
            // the
            getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[]) null);
        } catch (Exception ex) {
            getGeneratedKeysMethod = null;
        }
        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
    }

    @Override
    public void start() {
        // 将写好的sql语句赋值给insertSQL
        insertSQL = buildInsertSQL();
        super.start();
    }
    /**
     * 自己写新增sql语句,对应到logging数据表
     * */
    private static String buildInsertSQL() {
        return "INSERT INTO `log`(`message`,`level_string`,`created_time`,`logger_name`)" +
                "VALUES (?,?,?,?)";
    }
    @Override
    protected Method getGeneratedKeysMethod() {
        return GET_GENERATED_KEYS_METHOD;
    }

    @Override
    protected String getInsertSQL() {
        return insertSQL;
    }
    /**
     * 为buildInsertSQL()里面的？占位符填充数据，用数字指定填充的位置
     * */
    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
        // event.getFormattedMessage() 日志打印内容
        String message = event.getFormattedMessage();
        // 如果只想存储自己打印的日志，可以这样写日志：logger.info("- XXXX")
        // 判断日志消息首字母为 - 的日志，记录到数据库表
        if(message.startsWith(StartWithPaternThenSaveToDB)){
            stmt.setString(MESSAGE, message);
            // event.getLevel().toString() 日志级别
            stmt.setString(LEVEL_STRING, event.getLevel().toString());
            // new Timestamp(event.getTimeStamp()) 时间
            stmt.setTimestamp(CREATE_TIME, new Timestamp(event.getTimeStamp()));
            // event.getLoggerName() 全类名
            stmt.setString(LOGGER_NAME, event.getLoggerName());
        }

    }

    @Override
    protected void subAppend(ILoggingEvent eventObject, Connection connection, PreparedStatement statement) throws Throwable {
        bindLoggingEventWithInsertStatement(statement, eventObject);
        // This is expensive... should we do it every time?
        int updateCount = statement.executeUpdate();
        if (updateCount != 1) {
            addWarn("Failed to insert loggingEvent");
        }
    }

    @Override
    protected void secondarySubAppend(ILoggingEvent eventObject, Connection connection, long eventId) throws Throwable {

    }
}
