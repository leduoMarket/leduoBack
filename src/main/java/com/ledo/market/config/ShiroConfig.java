package com.ledo.market.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 王梦琼
 */
@Configuration
public class ShiroConfig {
    @Bean
    public UserRealm userRealm(){
        //获取需要比对的数据
        return new UserRealm();
    }
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //用manager对象管理userRealm里面的object
        manager.setRealm(userRealm());
        return manager;
    }
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        //配置过滤规则
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/home");
        bean.setUnauthorizedUrl("/login");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/login", "anon");
        map.put("/home*", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}
