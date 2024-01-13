package com.aaa.javabase.spring.Listener.注解;

import org.springframework.beans.factory.InitializingBean;
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
public class OrderListenerService implements InitializingBean {
    @Autowired
    private ApplicationContext context;

    public void publishOrder() {
        context.publishEvent(OrderEvent.builder().msg("建立订单1").build());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        /**
         * 这里其实没效果的，因为
         * com.aaa.javabase.spring.Listener.注解.OrderEventListener
         *  @EventListener 注解生成代理的堆栈如下：
         * java.lang.Thread.getStackTrace(Thread.java:1559)
         * com.aaa.javabase.util.ThreadUtil.printStackTrace(ThreadUtil.java:15)
         * org.springframework.context.event.EventListenerMethodProcessor.processBean(EventListenerMethodProcessor.java:171)
         * org.springframework.context.event.EventListenerMethodProcessor.afterSingletonsInstantiated(EventListenerMethodProcessor.java:134)
         * org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:896)
         * org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:878)
         * org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550)
         * org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:141)
         * org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747)
         * org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397)
         * org.springframework.boot.SpringApplication.run(SpringApplication.java:315)
         * com.aaa.javabase.JavabaseApplication.main(JavabaseApplication.java:34)
         * 可知：@EventListener 注解 是在
         * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#preInstantiateSingletons()
         *
         * // Trigger initialization of all non-lazy singleton beans...
         *     执行afterPropertiesSet
         * // Trigger post-initialization callback for all applicable beans...
         *     扫描@EventListener
         * 所以注解形式的监听器，无法使用afterPropertiesSet初始化
         *
         */
        context.publishEvent(OrderEvent.builder().msg("建立订单1").build());
    }
}
