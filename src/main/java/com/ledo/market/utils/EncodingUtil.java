package com.ledo.market.utils;
import com.ledo.market.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
/**
 * @author 王梦琼
 * 提供对用户名码二次加密的工具,传进user，根据用户名和密码生成盐值加密的密码
 * */

@Component
@Slf4j
public class EncodingUtil {
    //加密次数为99次
        private final int ENCODINGTIME = 99;
        public Object getPasswordEncoding(User user){
            String principal = user.getUid();
            String credentials = user.getPassword();
            ByteSource credentialsSalt = ByteSource.Util.bytes(principal);
            Object passwordResult = new SimpleHash("MD5",credentials,credentialsSalt,ENCODINGTIME);
            log.info("-注册时候获取的凭证为"+principal+"密码为："+credentials+"盐为:"+credentialsSalt+"数据库应该存储的密码为："+passwordResult);
            System.out.println("-数据库存储的密码："+passwordResult);
            return passwordResult;
    }
}
