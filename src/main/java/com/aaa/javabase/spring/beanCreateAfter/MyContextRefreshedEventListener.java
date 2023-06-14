package com.aaa.javabase.spring.beanCreateAfter;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 MyContextRefreshedEvent.java  2023/6/14 23:46
 */
@Component
public class MyContextRefreshedEventListener {

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("contextRefreshEvent");
    }
}
