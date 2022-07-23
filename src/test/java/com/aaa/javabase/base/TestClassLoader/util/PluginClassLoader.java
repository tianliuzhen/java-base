package com.aaa.javabase.base.TestClassLoader.util;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author liuzhen.tian
 * @version 1.0 PluginClassLoader.java  2022/7/23 15:04
 */
public class PluginClassLoader extends URLClassLoader {
    public PluginClassLoader(URL[] urls) {
        // 类加载器的双亲委派机制
        // 先使用父加载器加载class，加载不到时再调用findClass方法
        super(urls, null);
    }
}
