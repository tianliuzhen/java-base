package com.aaa.javabase.spring.autowriedAndResource;

import com.aaa.javabase.spring.autowriedAndResource.service.MyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * description:
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/6/16
 */

public class HelloTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext("com.aaa.javabase.spring.autowriedAndResource");
        MyService myService = ac.getBean(MyService.class);
        myService.say();
    }
}
