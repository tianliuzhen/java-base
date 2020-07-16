package com.aaa.javabase.processor;

/**
 * @author liuzhen.tian
 * @version $ Id: PostProcessor.java, v 0.1 2020/7/6 16:26 liuzhen.tian Exp $
 */
import com.aaa.javabase.service.GoodsService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 定义Bean初始化前后的动作,这里的BeanPostProcessor针对全局生效
 * @author typ
 */
@Component
public class MyInitializationPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {

        if (beanName.equals("goodsService")) {
            System.out.println("------------------------------");
            System.out.println("对象:" + beanName + "开始初始化");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (beanName.equals("goodsService")) {
            GoodsService goodsService= (GoodsService) bean;
            goodsService.setGoodsName("初始化完成的商品名字");
            System.out.println("对象:" + beanName + "初始化完成");
            System.out.println("------------------------------");
        }
        return bean;
    }

}
