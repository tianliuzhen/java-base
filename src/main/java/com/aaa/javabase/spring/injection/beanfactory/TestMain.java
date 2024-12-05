package com.aaa.javabase.spring.injection.beanfactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2024/12/5 20:43
 */
public class TestMain {
    /**
     * 构造注入
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanAppConfig.class);
        BeanAppConfig.AppleBean a1bean = (BeanAppConfig.AppleBean) ac.getBean("appleBean");
        System.out.println();
    }
}
