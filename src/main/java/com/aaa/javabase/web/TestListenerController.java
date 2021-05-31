package com.aaa.javabase.web;

import com.aaa.javabase.service.OrderService;
import com.aaa.javabase.spring.Listener.注解.OrderListenerService;
import com.aaa.javabase.spring.Listener.非注解.OrderListenerService2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试spring 监听器
 *
 * @author liuzhen.tian
 * @version 1.0 TestListerController.java  2021/5/31 20:35
 */
@RestController
@RequestMapping(value = "/listener")
public class TestListenerController {

    @Autowired
    OrderListenerService orderListenerService;

    @GetMapping(value = "/listenerOrder")
    public void listenerOrder() {
        orderListenerService.publishOrder();
    }

    @Autowired
    OrderListenerService2 orderListenerService2;

    @GetMapping(value = "/listenerOrder2")
    public void listenerOrder2() {
        orderListenerService2.publishOrder();
    }

}
