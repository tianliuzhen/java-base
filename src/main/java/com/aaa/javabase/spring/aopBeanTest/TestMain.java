package com.aaa.javabase.spring.aopBeanTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2023/9/18 21:51
 */
public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(AopAppConfig.class);
        MyCglibInterface bean1 = (MyCglibInterface) ac.getBean("myCglibBeanService");
        MyJavaBeanService bean = (MyJavaBeanService) ac.getBean("myJavaBeanService");
        System.out.println(bean.toString());
        System.out.println(bean1.toString());
    }
}
