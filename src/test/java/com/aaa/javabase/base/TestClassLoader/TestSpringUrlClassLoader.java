package com.aaa.javabase.base.TestClassLoader;

import com.aaa.javabase.base.TestClassLoader.util.ClassLoaderBeanUtil;
import com.aaa.javabase.pattern.behavior.strategy.annotion.ChooserName;
import com.aaa.javabase.util.BeanContextUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * 根据注解扫描spring ioc的几种方式
 * 1、基于 spring 的url加载器
 * 2、基于 ApplicationContext
 *
 * @author liuzhen.tian
 * @version 1.0 TestSpringUrlClassLoader.java  2022/7/22 21:58
 */
@SpringBootTest
public class TestSpringUrlClassLoader {


    /**
     * 方式一：
     * <p>
     * 根据 DefaultListableBeanFactory 的 BeanClassLoader 扫描注解类
     */
    @Test
    public void getBeanByAnnotation() {
        // spring 上下文
        ApplicationContext applicationContext = BeanContextUtil.getApplicationContext();
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();

        // classLoader.getClass().getSuperclass() 是  java.net.URLClassLoader
        ClassLoader beanClassLoader = beanFactory.getBeanClassLoader();

        // 通过反射工具 Reflections类 解析
        List<Class<?>> classes = ClassLoaderBeanUtil.getBeanByClassLoader(beanClassLoader, ChooserName.class);

    }

    /**
     * 方式二：
     * <p>
     * 根据 applicationContext 的 getBeansWithAnnotation 方法 扫描注解类
     */
    @Test
    public void getBeanByAnnotation2() {
        // spring 上下文
        ApplicationContext applicationContext = BeanContextUtil.getApplicationContext();

        List<Class<?>> classes = Lists.newArrayList();
        // 直接调用 getBeansWithAnnotation
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(ChooserName.class);
        beansWithAnnotation.values().forEach(e -> classes.add(e.getClass()));

        System.out.println();
    }
}
