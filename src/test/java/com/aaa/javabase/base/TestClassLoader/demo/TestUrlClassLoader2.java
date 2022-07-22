package com.aaa.javabase.base.TestClassLoader.demo;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 通过 URLClassLoader 加载外部jar，读取指定类
 *
 * @author liuzhen.tian
 * @version 1.0 TestUrlClassLoader.java  2022/7/21 20:39
 */
public class TestUrlClassLoader2 {
    public static void main(String[] args) throws Exception {
        // testError();
        testSuccess();
    }

    public static void testSuccess() throws Exception {
        // 创建一个URL数组
        File file = new File("F:\\WorkSpace\\MyGithub\\functions\\target\\functions-0.0.1-SNAPSHOT.jar");
        URL[] urls = new URL[]{file.toURI().toURL()};

        ClassLoader parent = TestUrlClassLoader2.class.getClassLoader().getParent();
        ClassLoader classLoader = TestUrlClassLoader2.class.getClassLoader();
        URLClassLoader myClassLoader = new URLClassLoader(urls, parent);
        Class<?> aClass = myClassLoader.loadClass("com.aaa.functions.service.CommonUrlParse");
        Object obj = aClass.newInstance();

        // 利用反射创建对象
        Method method = aClass.getMethod("parse");

        //获取parse方法
        Object xxx = method.invoke(obj);

        System.out.println(xxx);
    }

    /**
     * myClassLoader 的 parent 是 AppClassLoader
     * 根据坑爹模式，myClassLoader 加载 JsonFactory 之前，会先让 AppClassLoader 去加载
     * 而 AppClassLoader 从自己的 classpath 找到了这个类，加载成功，不过版本是2.5.4
     * 但是2.5.4的版本中并没有getFormatGeneratorFeatures方法，所以。。。。
     */
    public static void testError() throws Exception {
        // 创建一个URL数组
        File file = new File("F:\\WorkSpace\\MyGithub\\functions\\target\\functions-0.0.1-SNAPSHOT.jar");
        URL[] urls = new URL[]{file.toURI().toURL()};

        // 这时候 myClassLoader 的 parent 是 AppClassLoader
        URLClassLoader myClassLoader = new URLClassLoader(urls);
        Class<?> aClass = myClassLoader.loadClass("com.aaa.functions.service.CommonUrlParse");
        Object obj = aClass.newInstance();

        // 利用反射创建对象
        Method method = aClass.getMethod("parse");

        //获取parse方法
        Object xxx = method.invoke(obj);

        System.out.println(xxx);
    }
}
