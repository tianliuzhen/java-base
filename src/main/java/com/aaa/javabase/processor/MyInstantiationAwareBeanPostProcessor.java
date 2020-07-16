package com.aaa.javabase.processor;

import com.aaa.javabase.service.GoodsService;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * 定义Bean实例化前后的动作,这里的BeanPostProcessor针对全局生效
 * @author liuzhen.tian
 * @version 1.0    2020/7/16 21:38
 */
@Component
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        if (beanName.equals("goodsService")) {
            System.out.println(this.getClass().getName() + ".postProcessBeforeInstantiation()被调用 了...");

        }
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("goodsService")) {
            System.out.println(this.getClass().getName() + ".postProcessAfterInitialization()被调用 了...");

            GoodsService goodsService= (GoodsService) bean;
            goodsService.setGoodsName("实例化之后完成的商品名字");
        }

        return super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
            throws BeansException {
        // System.out.println(this.getClass().getName() + ".postProcessProperties()被调用 了...");
        return super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
                                                    String beanName) throws BeansException {
        // System.out.println(this.getClass().getName() + ".postProcessPropertyValues()被调用 了...");
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}
