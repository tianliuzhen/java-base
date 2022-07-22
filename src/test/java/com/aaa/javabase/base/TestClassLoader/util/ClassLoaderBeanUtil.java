package com.aaa.javabase.base.TestClassLoader.util;

import com.aaa.javabase.util.ReflectionUtil;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author liuzhen.tian
 * @version 1.0 BeanUtil.java  2022/7/22 22:34
 */
public class ClassLoaderBeanUtil {
    public static List<Class<?>> getBeanByClassLoader(ClassLoader classLoader, Class<? extends Annotation> annotation) {
        URL[] urls = null;
        try {
            Method getUrlClassLoader = ReflectionUtil.getMethod(classLoader.getClass(), "getURLs");
            urls = (URL[]) getUrlClassLoader.invoke(classLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ConfigurationBuilder config = new ConfigurationBuilder();
        // 必填参数：jar的路径：
        config.addUrls(urls);
        // 必填参数：ClassLoader
        config.addClassLoader(classLoader);
        Reflections reflections = new Reflections(config);
        // 扫描注解类
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(annotation);
        return new ArrayList<>(typesAnnotatedWith);
    }

}
