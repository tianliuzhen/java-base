package com.aaa.javabase.processor;

import com.aaa.javabase.service.GoodsService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * BeanFactoryPostProcessor 主要是在对象实例化前对beanDefinition中的元数据进行修改
 * 针对某个bean 在  BeanFactoryPostProcessor  设置完之后，
 * 在BeanPostProcessor进一步设置无效
 *
 * @author liuzhen.tian
 * @version 1.0    2020/7/16 21:19
 */
@Component
public class MyBeanFactoryPostProcessorV2  implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        // GoodsService goodsService= (GoodsService) beanFactory.getBean("goodsService");
        // goodsService.setGoodsName("newGoodsName");
    }
}
