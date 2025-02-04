package com.aaa.javabase.spring.泛型注入;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2020/11/25 11:49
 */
public class TestMain {

    /**
     * 构造注入
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.aaa.javabase.spring.泛型注入");
        String[] beanNamesForType = ac.getBeanNamesForType(FxService.class);
        FxFactory bean = ac.getBean(FxFactory.class);
    }

}
