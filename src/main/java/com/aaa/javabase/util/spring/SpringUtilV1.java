package com.aaa.javabase.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 不推荐使用 遇见 @PostConstruct 会发生空指针，
 * 但是当前类上可以使用 @DependsOn("springUtilV1") 来规避
 *
 * @author liuzhen.tian
 * @version 1.0 BeanContextUtil.java  2022/7/22 22:27
 */
@Component
public class SpringUtilV1 implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtilV1.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clz) {
        return (T) applicationContext.getBean(clz);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
