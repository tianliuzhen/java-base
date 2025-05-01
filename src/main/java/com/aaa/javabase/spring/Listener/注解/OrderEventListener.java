package com.aaa.javabase.spring.Listener.注解;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 事件处理程序
 *
 * @author liuzhen.tian
 * @version 1.0 OrderEventListener.java  2021/5/31 20:33
 */
@Component
public class OrderEventListener  implements InitializingBean {

    @Order(1)
    @EventListener
    public void handleOrderEvent1(OrderEvent event) {
        System.out.println("我监听到了handleOrderEvent1发布的message为:" + event.getMsg());
    }

    @Order(0)
    @EventListener
    public void handleOrderEvent0(OrderEvent event) {
        System.out.println("我监听到了handleOrderEvent0发布的message为:" + event.getMsg());
    }

    @Order(2)
    @EventListener
    public void handleOrderEvent2(OrderEvent event) {
        System.out.println("我监听到了handleOrderEvent2发布的message为:" + event.getMsg());
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(1);
    }
}
