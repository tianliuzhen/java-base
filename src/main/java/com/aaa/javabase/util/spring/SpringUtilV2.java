package com.aaa.javabase.util.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 不推荐使用 遇见 @PostConstruct 会发生空指针
 *
 * @author liuzhen.tian
 * @version 1.0 BeanContextUtil.java  2022/7/22 22:27
 */
@Component
public class SpringUtilV2 implements ApplicationListener<ApplicationEvent> {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            ContextRefreshedEvent e = (ContextRefreshedEvent) event;
            if (e.getApplicationContext().getParent() == null) {
                applicationContext = e.getApplicationContext();
            }
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }


}
