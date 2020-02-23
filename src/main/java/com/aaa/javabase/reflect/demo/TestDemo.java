package com.aaa.javabase.reflect.demo;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/18
 */
public class TestDemo {
    public static void main(String[] args) throws Exception {
        // 可以创建任意类的对象，可以执行任意方法
        //传统方法
        /**
         * 前提、不能改变任何代码，可以创建任意类的对象，可以执行任意方法
             Person person = new Person();
             person.m1();
         */
        //1. 加载配置
           //1.1 创建 Properties 对象
        Properties properties = new Properties();
           //1.2 加载配置文件，转为一个集合
               //1.2.1 获取class目录下的配置路径
        ClassLoader classLoader = TestDemo.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("pro.properties");
        properties.load(resourceAsStream);
        //2. 获取配置文件中定义的路径
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");

        //3. 加载类进内存
        Class<?> aClass = Class.forName(className);
        //4. 创建对象
        Object obj = aClass.newInstance();
        //5. 获取对象方法
        Method method = aClass.getMethod(methodName);
        //6. 执行方法
        method.invoke(obj);

    }
}
