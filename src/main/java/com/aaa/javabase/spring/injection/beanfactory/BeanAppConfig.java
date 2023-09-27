package com.aaa.javabase.spring.injection.beanfactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author liuzhen.tian
 * @version 1.0 BeanAppConfig.java  2023/9/13 21:12
 */
@Configuration
public class BeanAppConfig {

    @Bean
    public AppleBean appleBean1(@Lazy AppleBean appleBean2) {
       return new AppleBean();
    }

    @Bean
    public AppleBean appleBean2(AppleBean appleBean1) {
        return  new AppleBean();
    }

    public class AppleBean {
    }

}
