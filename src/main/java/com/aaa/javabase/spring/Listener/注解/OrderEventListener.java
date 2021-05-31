package com.aaa.javabase.spring.Listener.注解;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事件处理程序
 *
 * @author liuzhen.tian
 * @version 1.0 OrderEventListener.java  2021/5/31 20:33
 */
@Component
public class OrderEventListener {
    @EventListener
    public void handleOrderEvent(OrderEvent event) {
        System.out.println("我监听到了handleOrderEvent发布的message为:" + event.getMsg());
    }
}
