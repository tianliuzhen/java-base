package com.aaa.javabase.spring.Listener.非注解;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * implements ApplicationListener<> 形式进行监听
 *
 * @author liuzhen.tian
 * @version 1.0 OrderEventListener2.java  2021/5/31 20:38
 */
@Component
public class OrderEventListener2 implements ApplicationListener<OrderEvent2> {
    @Override
    public void onApplicationEvent(OrderEvent2 event) {
        System.out.println("我监听到了handleOrderEvent2发布的message为:" + event.getMsg());
    }
}
