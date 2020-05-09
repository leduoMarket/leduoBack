package com.ledo.market.config;
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
        //设置安全管理器
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
        filterBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterBean.setUnauthorizedUrl("/login");
        filterBean.setLoginUrl("/");
        filterMap.put("/emps", "authc");
        filterMap.put("/logout", "authc");
        filterMap.put("/stock", "authc");
        System.out.println("过滤器拦截stock");
        filterBean.setSuccessUrl("/emps");
        filterBean.setFilterChainDefinitionMap(filterMap);
        return filterBean;
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm());
        return manager;
    }
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
    @Bean
    public SessionManager sessionManager() {
        return null;
    }

//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager security){
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(security);
//        return advisor;
//    }
}
