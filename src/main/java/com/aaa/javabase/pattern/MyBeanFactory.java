package com.aaa.javabase.pattern;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 通用类工厂获取 spring bean
 *
 * @author liuzhen.tian
 * @version 1.0 BeanFactory.java  2021/11/5 22:11
 */
@Component
public class MyBeanFactory<k, v> implements ApplicationContextAware {

    /**
     * 上下文
     */
    private ApplicationContext applicationContext;

    private HashMap<k, v> map = Maps.newHashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 根据接口获取spring所有实现类bean
     *
     * @param kClass class
     * @param <v>    泛型参数
     * @return List<v>
     */
    public <v> List<v> getBeansOfType(Class<v> kClass) {

        // 1. 获取所有bean
        Map<String, v> map = applicationContext.getBeansOfType(kClass);

        // 2. 返回 （map 转 list）
        return map.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
    }
}
