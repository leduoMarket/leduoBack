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
        //登录失败跳转
        filterBean.setLoginUrl("/identifyFailed");
        //login请求任何人都可以访问
        filterMap.put("/login","anon");
        //设置未授权的访问页面
        filterBean.setUnauthorizedUrl("/noauth");
        //资源根据角色来分类
        filterMap.put("/staff/**","roles[staff]");
        filterMap.put("/treasure/**","roles[treasure,staff]");
        filterMap.put("/admin/**","roles[admin,staff,treasure]");
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
