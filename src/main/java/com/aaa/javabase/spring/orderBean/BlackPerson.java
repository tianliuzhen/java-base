package com.aaa.javabase.spring.orderBean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 BlackPersion.java  2020/12/11 15:22
 */
@Component
@Order(1)
public class BlackPerson implements CommandLineRunner {
    @Override
    public void run(String... args)  {
        System.out.println("----BlackPersion----");
    }
}
