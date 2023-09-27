package com.aaa.javabase.spring.aopBeanTest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author liuzhen.tian
 * @version 1.0 AppConfig.java  2022/10/11 21:24
 */

/**
 * 用于启动通过main函数启动
 * AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(AopAppConfig.class);
 * 来启动一个spring容器
 * 不同于@SpringBootApplication，
 * 因为springBoot默认从spring.factories文件里面默认自动注入，已经包含了@EnableAspectJAutoProxy、@EnableTransactionManagement
 */
@ComponentScan("com.aaa.javabase.spring")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AopAppConfig {

    public AopAppConfig() {
        System.out.println();
    }
}
