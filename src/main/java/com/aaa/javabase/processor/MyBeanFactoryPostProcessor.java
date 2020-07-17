package com.aaa.javabase.processor;

import com.aaa.javabase.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 *
 * @author liuzhen.tian
 * @version 1.0    2020/7/14 21:04
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //这里获取未定义的 beanDefinition
        GenericBeanDefinition orderService = (GenericBeanDefinition) beanFactory.getBeanDefinition("orderService");
        //这里把 OrderService 转成 UserService
        orderService.setBeanClass(UserService.class);
    }
}
