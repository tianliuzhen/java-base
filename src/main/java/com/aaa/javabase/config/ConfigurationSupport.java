package com.aaa.javabase.config;

import com.aaa.javabase.domain.Demo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * name 不存在
 *      matchIfMissing=true，会加载
 *      matchIfMissing=false，不会加载
 *
 * name 存在
 *     匹配 havingValue， matchIfMissing=true，会加载
 *     不匹配 havingValue， matchIfMissing=true，不会加载
 *
 *     匹配 havingValue， matchIfMissing=false，会加载
 *     不匹配 havingValue， matchIfMissing=false，不会加载
 *
 * @author liuzhen.tian
 * @version 1.0 ConfigurationSupport.java  2020/12/13 22:46
 */
@Configuration
@ConditionalOnProperty(name = "spring.aaa", havingValue = "aaa", matchIfMissing = false)
public class ConfigurationSupport {

    @Bean
    public Demo getDemo() {
        return new Demo();
    }
}
