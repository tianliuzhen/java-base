package com.aaa.javabase.base.TestClassLoader.demo;

import org.apache.commons.lang3.StringUtils;

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

        // /加载插件包  读取位置： resources/plugin/plugin-provider.jar
        // ClassPathResource resource = new ClassPathResource("plugin/plugin-provider.jar");
        // 打印插件包路径
        // System.out.println(resource.getURL().getPath());

        // 打印当前类加载器
        System.out.println("Boot: " + TestUrlClassLoader2.class.getClass().getClassLoader());
        // 获取StringUtils的类全路径
        System.out.println("Boot: " + StringUtils.class.getResource("").getPath());
        // 模拟调用插件包

        // testError();
        testSuccess();
    }

    public static void testSuccess() throws Exception {
        // 创建一个URL数组
        File file = new File("F:\\WorkSpace\\MyGithub\\functions\\target\\functions-0.0.1-SNAPSHOT.jar");
        URL[] urls = new URL[]{file.toURI().toURL()};

        URLClassLoader myClassLoader = new URLClassLoader(urls, null);

        // URLClassLoader myClassLoader = new PluginClassLoader(urls);

        // ClassLoader originClassLoader = Thread.currentThread().getContextClassLoader();
        /**
         这里需要临时更改当前线程的 ContextClassLoader
         避免中间件代码中存在Thread.currentThread().getContextClassLoader()获取类加载器
         因为它们会获取当前线程的 ClassLoader 来加载 class，而当前线程的ClassLoader极可能是App ClassLoader而非自定义的ClassLoader,
         也许是为了安全起见，但是这会导致它可能加载到启动项目中的class（如果有），或者发生其它的异常，所以我们在执行时需要临时的将当前线程的ClassLoader设置为自定义的ClassLoader，以实现绝对的隔离执行
         */
        // Thread.currentThread().setContextClassLoader(myClassLoader);


        Class<?> aClass = myClassLoader.loadClass("com.aaa.functions.util.StringUtil");
        Object obj = aClass.newInstance();

        // 利用反射创建对象
        Method method = aClass.getMethod("parse");

        //获取parse方法
        Object xxx = method.invoke(obj);

        System.out.println(xxx);
        // Thread.currentThread().setContextClassLoader(originClassLoader);
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
        URLClassLoader myClassLoader = new URLClassLoader(urls);
        Class<?> aClass = myClassLoader.loadClass("com.aaa.functions.util.StringUtil");
        Object obj = aClass.newInstance();
        // 利用反射创建对象
        Method method = aClass.getMethod("parse");

        //获取parse方法
        Object xxx = method.invoke(obj);

        System.out.println(xxx);
    }
}
