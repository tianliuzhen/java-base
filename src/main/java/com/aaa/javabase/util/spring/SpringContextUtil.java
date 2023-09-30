package com.aaa.javabase.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 SpringContextUtil.java  2023/9/29 23:06
 */
@Component
public class SpringContextUtil implements BeanFactoryPostProcessor, ApplicationContextAware, Ordered {

    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringContextUtil.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }




    public ListableBeanFactory getBeanFactory() {
        return null == beanFactory ? applicationContext : beanFactory;
    }

    public <T> T getBean(String name) {
        return (T) getBeanFactory().getBean(name);
    }

    public <T> T getBean(Class<T> clazz) {
        return getBeanFactory().getBean(clazz);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
