package com.ledo.market.config;

import com.ledo.market.entity.User;
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
    /*拦截请求*/
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager ) {
        //设置安全管理器
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
        filterBean.setSecurityManager(securityManager);

        //添加shiro的内置过滤器
        /*
        * 1、anon :无需认证就可以访问
        * 2、authc:必须认证了才能用
        * 3、user:必须拥有记住我功能才能用
        * 4、perms:拥有对某个资源的权限访问
        * 5、拥有某个角色权限才能访问
        * */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //login页面任何人都能访问
        filterMap.put("/login", "anon");
        //home页面需要认证才可以访问
        filterMap.put("/home*", "authc");
        //失败之后的路由
        filterBean.setLoginUrl("/login");
        filterBean.setSuccessUrl("/home");
        filterBean.setUnauthorizedUrl("/login");
        filterBean.setFilterChainDefinitionMap(filterMap);
        return filterBean;
    }

    /*
    * Qualifier从bean托管中找到userRealm对象
    * 权限管理，组合了登录、登出、权限的管理
    * */
    @Bean
    DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //用manager对象管理userRealm里面的object
        manager.setRealm(userRealm);
        return manager;
    }

    //自定义realm对象
    // 这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理
    @Bean
    public UserRealm userRealm(){
        //获取需要比对的数据
        return new UserRealm();
    }
    /**
     * 密码凭证匹配器，作为自定义认证的基础 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义sessionManager，用户的唯一标识，即Token或Authorization的认证
     */
    @Bean
    public SessionManager sessionManager() {
//        MySessionManager mySessionManager = new MySessionManager();
//        return mySessionManager;
        return null;
    }

}
