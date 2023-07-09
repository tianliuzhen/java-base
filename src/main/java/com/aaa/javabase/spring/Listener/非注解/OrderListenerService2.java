package com.aaa.javabase.spring.Listener.非注解;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author liuzhen.tian
 * @version 1.0 OrderListenerService2.java  2021/5/31 20:40
 */

@Service
public class OrderListenerService2 implements InitializingBean {
    @Autowired
    private ApplicationContext context;



    public void publishOrder() {
        context.publishEvent(new OrderEvent2(context,"建立订单2"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        context.publishEvent(new OrderEvent2(context,"@PostConstruct 建立订单2"));
    }
}
