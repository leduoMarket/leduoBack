package com.ledo.market.config;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;

/**
 * @author 王梦琼
 */
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(-100)
@Component
@ServletComponentScan
public class WebMvcConfigure implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PATCH,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        // prefight请求
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus( 200 );
            return; }
        chain.doFilter(req, res);
    }
    @Override
    public void destroy() { }
}
//@Configuration
//public class WebMvcConfigure extends WebMvcConfigurationSupport {
//    /**
//     * 配置跨域请求支持
//     **/
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH","OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true)
//                .exposedHeaders(HttpHeaders.SET_COOKIE)
//                .maxAge(3600L);
//    }
//}
