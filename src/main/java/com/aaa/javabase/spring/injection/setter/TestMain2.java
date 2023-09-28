package com.aaa.javabase.spring.injection.setter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2020/11/25 11:49
 */
public class TestMain2 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SetterConfig.class);
        // A1bean a1bean = ac.getBean(A1bean.class);
        // A2bean a2bean = ac.getBean(A2bean.class);
        // A3bean a3bean = ac.getBean(A3bean.class);
        Ebean a4bean = ac.getBean(Ebean.class);
        System.out.println();
    }

}
