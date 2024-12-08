package com.aaa.javabase.spring.injection.beanfactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzhen.tian
 * @version 1.0 BeanAutowireCandidate.java  2024/12/8 9:37
 */
@Configuration
public class BeanAutowireCandidate {

    @AllArgsConstructor
    @Data
    public class BacBean {
        private String name;
    }

    @Bean(autowireCandidate = false)
    public Object bacObject() {
        return new BacBean("bacObject");
    }


    @Bean
    public Object bacObject1() {
        System.out.println(bacObject());
        return new Object();
    }

    @Bean
    public Object bacObject2() {
        System.out.println(bacObject());
        return new Object();
    }

    /**
     @Bean(autowireCandidate = false)里面的 autowireCandidate 默认是true，如果是false，无法被自动注入，只能手动出入
     源码分析：
     org.springframework.beans.factory.support.AbstractBeanDefinition#isAutowireCandidate()
     org.springframework.beans.factory.support.DefaultListableBeanFactory#findAutowireCandidates(java.lang.String, java.lang.Class, org.springframework.beans.factory.config.DependencyDescriptor)
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanAutowireCandidate.class);
        BacBean a1bean = (BacBean) ac.getBean("bacObject1");
        System.out.println();
    }
}
