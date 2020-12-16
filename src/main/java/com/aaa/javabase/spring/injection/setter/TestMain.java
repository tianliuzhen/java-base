package com.aaa.javabase.spring.injection.setter;

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
        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext();
        ac.register(Cbean.class);
        ac.register(Dbean.class);
        ac.refresh();
        Cbean cbean = ac.getBean(Cbean.class);
        cbean.get();
        Dbean dbean = ac.getBean(Dbean.class);
        dbean.get();
    }

}
