package com.aaa.javabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author liuzhen.tian
 * @version 1.0 CrossOriginConfig.java  2024/9/26 21:36
 */
@Configuration
public class CrossOriginConfig {

    /**
     * 允许跨域调用的过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 明确指定允许的域，或者使用allowedOriginPatterns指定域名模式
        // 例如: config.addAllowedOrigin("https://example.com");
        // 使用allowedOriginPatterns允许多个域名
        config.addAllowedOrigin("*"); // 或者具体的域名模式，如"https://*.example.com"
        config.setAllowCredentials(true); // 允许跨域发送cookie
        config.addAllowedHeader("*"); // 放行全部原始头信息
        config.addAllowedMethod("*"); // 允许所有请求方法跨域调用

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
