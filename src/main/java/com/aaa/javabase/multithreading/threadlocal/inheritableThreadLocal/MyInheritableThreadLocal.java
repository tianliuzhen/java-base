package com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal;


import com.alibaba.fastjson.JSONObject;


/**
 * 在子线程返回主线程的ThreadLocal值中，做隔离，本质就是修改ThreadLocal value中的引用
 * java.lang.ThreadLocal.ThreadLocalMap#ThreadLocalMap(java.lang.ThreadLocal.ThreadLocalMap)
 *
 * @author liuzhen.tian
 * @version 1.0 MyInheritableThreadLocal.java  2020/10/25 0:54
 */
public class MyInheritableThreadLocal<T> extends InheritableThreadLocal<T> {

    @Override
    protected T childValue(T parentValue) {
        String s = JSONObject.toJSONString(parentValue);
        return (T) JSONObject.parseObject(s, parentValue.getClass());
    }
}

