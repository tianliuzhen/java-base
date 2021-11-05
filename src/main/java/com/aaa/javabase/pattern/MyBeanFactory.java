package com.aaa.javabase.pattern;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
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

        // 可拓展，类的加载顺序，使用 @Order 即可
        // map.get("deleteStatusHandler").getClass().getAnnotation(Order.class).value()

        // 2. 返回 （map 转 list）
        return map.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
    }

    /**
     * 根据接口获取spring所有实现类bean
     * (按照 @Order value 拍序)
     *
     * @param kClass class
     * @param <v>    泛型参数
     * @return List<v>
     */
    public <v> List<v> getBeansOfTypeAsc(Class<v> kClass) {
        // 1. 获取所有bean
        Map<String, v> map = applicationContext.getBeansOfType(kClass);

        // 2. 以 @Order 值为key，生成新的map
        Map<Integer, v> orderMap = map.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getValue().getClass().getAnnotation(Order.class).value()
                        , Map.Entry::getValue));

        // 3. 排序
        Map<Integer, v> orderMap2 = Maps.newHashMap();
        orderMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(e -> orderMap2.put(e.getKey(), e.getValue()));


        // 4. 返回 （map 转 list）
        return orderMap2.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
    }

}
