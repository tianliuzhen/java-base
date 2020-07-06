package com.aaa.javabase.service;

/**
 * @author liuzhen.tian
 * @version $ Id: PostProcessor.java, v 0.1 2020/7/6 16:26 liuzhen.tian Exp $
 */
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 定义Bean初始化前后的动作,这里的设置是全局的
 * @author typ
 */
// @Component
public class PostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("------------------------------");
        System.out.println("对象" + beanName + "开始实例化");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("对象" + beanName + "实例化完成");
        System.out.println("------------------------------");
        return bean;
    }

}
