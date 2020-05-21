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
            return passwordResult;
    }
}
