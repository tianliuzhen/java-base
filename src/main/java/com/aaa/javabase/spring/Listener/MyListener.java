package com.aaa.javabase.spring.Listener;

import com.aaa.javabase.spring.Listener.非注解.OrderEvent2;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 MyListener.java  2021/5/31 20:46
 */
@Component
public class MyListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("全局监听..." + applicationEvent);
        if (applicationEvent instanceof OrderEvent2){
            System.out.println("全局监听...OrderEvent2" );
        }
    }
}
