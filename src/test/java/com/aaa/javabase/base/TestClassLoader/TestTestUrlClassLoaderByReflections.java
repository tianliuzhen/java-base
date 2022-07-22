package com.aaa.javabase.base.TestClassLoader;

import com.aaa.javabase.function.annotion.FunctionDefinition;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

/**
 * 通过 URLClassLoader 加载外部jar，读取和执行指定带注解的类
 *
 * @author liuzhen.tian
 * @version 1.0 Test3.java  2022/7/21 22:17
 */
public class TestTestUrlClassLoaderByReflections {
    public static void main(String[] args) throws Exception {
        // 设置路径
        URL url = new File("F:\\WorkSpace\\MyGithub\\functions\\target\\functions-0.0.1-SNAPSHOT.jar").toURI().toURL();
        URL[] urls = new URL[]{url};

        // 设置url类加载器
        URLClassLoader myClassLoader = new URLClassLoader(urls);

        ConfigurationBuilder config = new ConfigurationBuilder();
        // 必填参数：jar的路径：
        config.addUrls(urls);
        // 必填参数：ClassLoader
        config.addClassLoader(myClassLoader);
        Reflections reflectionsx = new Reflections(config);

        // 通过反射工具 Reflections：扫描注解类
        Set<Class<?>> classSet = reflectionsx.getTypesAnnotatedWith(FunctionDefinition.class);
        // 通过反射工具 Reflections：扫描注解接口
        // Set<Class<? extends FunctionExecutorService>> subTypesOf = reflectionsx.getSubTypesOf(FunctionExecutorService.class);

        for (Class<?> aClass : classSet) {
            Object obj = aClass.newInstance();

            // 利用反射创建对象
            Method method = aClass.getMethod("execute", String.class);

            // 获取parse方法
            Object xxx = method.invoke(obj, "xxx");

            System.out.println(xxx);
        }
    }
}
