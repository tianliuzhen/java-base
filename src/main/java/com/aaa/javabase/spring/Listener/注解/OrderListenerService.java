package com.aaa.javabase.spring.Listener.注解;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 事件触发
 *
 * @author liuzhen.tian
 * @version 1.0 OrderListenerService.java  2021/5/31 20:34
 */
@Service
public class OrderListenerService {
    @Autowired
    private ApplicationContext context;

    public void publishOrder() {
        context.publishEvent(OrderEvent.builder().msg("建立订单").build());
    }
}
