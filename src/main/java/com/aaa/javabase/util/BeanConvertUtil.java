package com.aaa.javabase.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 BeanConvertUtil.java  2023/9/1 21:55
 */
public class BeanConvertUtil {
    public static <T> T beanTo(Object obj, Class<T> tClass) {
        return JSONObject.parseObject(JSONObject.toJSONString(obj), tClass);
    }

    public static <T> List<T> beanTo(List objs, Class<T> tClass) {
        List<T> list = Lists.newArrayList();
        for (Object obj : objs) {
            try {
                T t = tClass.newInstance();
                BeanCopyUtil.copyProperties(obj, t, false);
                list.add(t);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Object> objects = Lists.newArrayList("1");
        for (Object object : objects) {
            objects.remove(object);
        }

        Iterator<Object> iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            iterator.remove();
        }
    }
}
