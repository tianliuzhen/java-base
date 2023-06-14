package com.aaa.javabase.spring.beanCreateAfter;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 SmartInitializingSingletonExtend.java  2023/6/14 23:45
 */
@Component
public class MySmartInitializingSingleton implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("MySmartInitializingSingleton...");
    }
}
