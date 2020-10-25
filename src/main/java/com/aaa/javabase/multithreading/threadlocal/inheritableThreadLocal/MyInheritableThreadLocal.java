package com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal;


import com.alibaba.fastjson.JSONObject;


/**
 * @author liuzhen.tian
 * @version 1.0 MyInheritableThreadLocal.java  2020/10/25 0:54
 */
public class MyInheritableThreadLocal<T> extends InheritableThreadLocal<T> {
    protected T childValue(T parentValue) {
        String s = JSONObject.toJSONString(parentValue);
        return (T) JSONObject.parseObject(s,parentValue.getClass());
    }
}

