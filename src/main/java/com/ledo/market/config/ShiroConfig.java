package com.ledo.market.config;
import com.ledo.market.entity.Roles;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * @author 王梦琼
 * 编写shiro的配置类
 */
@Configuration
public class ShiroConfig {
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager ) {
        //设置安全管理器,包含认证管理器和授权管理器
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
        filterBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterBean.setLoginUrl("/identifyFailed");
        filterBean.setUnauthorizedUrl("/noauth");
        filterMap.put("/login","anon");
        filterMap.put("/home/staff/*","roles[staff]");
        filterMap.put("/home/treassure/*","roles[treassure]");
        filterMap.put("/home/admin*","roles[admin]");
        filterMap.put("/**", "authc");
        System.out.println("授权和认证拦截器");
        filterBean.setFilterChainDefinitionMap(filterMap);
        return filterBean;
    }

    @Bean
    DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm);
        return manager;
    }
    @Bean
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(99);
        System.out.println("使用MD5进行加密");
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
    @Bean
    public SessionManager sessionManager() {
        return null;
    }
}
