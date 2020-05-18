package com.ledo.market.config;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.db.DBAppenderBase;
import com.ledo.market.entity.User;
import com.ledo.market.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
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
@Slf4j
public class LogDBAppender extends DBAppenderBase<ILoggingEvent> {
    @Resource
    UserMapper userMapper;
    private static final Method GET_GENERATED_KEYS_METHOD;
    /**
     * 插入的sql语句
     */
    private String insertSQL;
    /**
     * message 日志内容
     * */
    private static final int MESSAGE = 1;
    /**
     * levelString,级别
     * */
    private static final int LEVEL_STRING = 2;
    // created_time 时间
    /**
     * 创建时间CREATE_TIME
     * */
    private static final int CREATE_TIME = 3;
    /**
     * logger_name 全类名
     * */
    private static final int LOGGER_NAME = 4;
    private static final int HANDLER_NAME = 5;

    /**
     * 以-开头则记录到数据库文件中
     * */
    static final String START_WITH_PATERN_THEN_SAVE_TODB = "-";

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
        return "INSERT INTO `log`(`message`,`level_string`,`created_time`,`logger_name`,`handler`)" +
                "VALUES (?,?,?,?,?)";
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
       //获取当前用户的用户名
        Subject currentUser = SecurityUtils.getSubject();
        String message = event.getFormattedMessage();
        if(message.startsWith(START_WITH_PATERN_THEN_SAVE_TODB)){
            stmt.setString(MESSAGE, message);
            stmt.setString(LEVEL_STRING, event.getLevel().toString());
            stmt.setTimestamp(CREATE_TIME, new Timestamp(event.getTimeStamp()));
            stmt.setString(LOGGER_NAME, event.getLoggerName());
            stmt.setString(HANDLER_NAME,currentUser.getPrincipal().toString());
        }
        System.out.println("currentuser:"+currentUser.getPrincipal().toString());
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
