package com.aaa.javabase.spring.injection.beanfactory;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author liuzhen.tian
 * @version 1.0 BeanAppConfig.java  2023/9/13 21:12
 */
@Configuration
public class BeanAppConfig {
    @Bean
    public AppleBean appleBean(AppleBean appleBean1, ObjectProvider<AppleBean> appleBean2) {
        AppleBean ifUnique = appleBean2.getIfAvailable();
        return new AppleBean();
    }


    @Bean
    public AppleBean appleBean1() {
        return new AppleBean();
    }

    @Bean
    public AppleBean appleBean2() {
        return new AppleBean();
    }


    public class AppleBean {
    }



}
