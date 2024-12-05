package com.aaa.javabase.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuzhen.tian
 * @version 1.0 CrossOriginConfig.java  2024/9/26 21:36
 */
// @Configuration
public class CrossOriginConfig {
    /**
     * 跨域设置
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

    public class CorsFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String originHeader = httpRequest.getHeader("Origin");
            //允许某个域名发起跨域请求
            httpResponse.addHeader("Access-Control-Allow-Origin", StringUtils.isEmpty(originHeader)?"*":originHeader);
            //设置允许Cookie
            httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
            //设置允许跨域请求的方法
            httpResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            //允许跨域请求包含content-type
            httpResponse.addHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,X-Token");
            //表示可以缓存时长，单位秒，表示隔30分钟才发起预检请求
            httpResponse.addHeader("Access-Control-Max-Age", "3600");
            //跨域的预检请求直接返回
            if ("OPTIONS".equals(httpRequest.getMethod())) {
                response.getWriter().println("ok");
                return;
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
        }
    }
}
