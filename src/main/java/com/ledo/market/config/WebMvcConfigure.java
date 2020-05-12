package com.ledo.market.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 王梦琼
 */
@Configuration
public class WebMvcConfigure extends WebMvcConfigurationSupport {
    /**
     * 配置跨域请求支持
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("*").allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L);
    }
}
