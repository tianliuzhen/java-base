package com.aaa.javabase.base.TestClassLoader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 通过 URLClassLoader 加载外部jar，读取和执行指定类
 *
 * @author liuzhen.tian
 * @version 1.0 TestUrlClassLoader.java  2022/7/21 20:39
 */
public class TestUrlClassLoader {
    public static void main(String[] args) throws Exception {
        // 创建一个URL数组
        File file = new File("F:\\WorkSpace\\MyGithub\\functions\\target\\functions-0.0.1-SNAPSHOT.jar");
        URL[] urls = new URL[]{file.toURI().toURL()};

        // 这时候 myClassLoader 的 parent 是 AppClassLoader
        URLClassLoader myClassLoader = new URLClassLoader(urls);
        Class<?> aClass = myClassLoader.loadClass("com.aaa.functions.service.CommonUrlParse");
        Object obj = aClass.newInstance();

        // 利用反射创建对象
        Method method = aClass.getMethod("execute", String.class);

        //获取parse方法
        Object xxx = method.invoke(obj, "xxx");

        System.out.println(xxx);
    }
}
