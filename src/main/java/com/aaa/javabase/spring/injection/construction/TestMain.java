package com.aaa.javabase.spring.injection.construction;

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
        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext("com.aaa.javabase.spring");
        Abean abean = ac.getBean(Abean.class);
        Bbean bbean = ac.getBean(Bbean.class);
        abean.get();
        bbean.get();
    }

}
