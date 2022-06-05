package com.aaa.javabase.base.引用传递误区;

import java.util.HashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain2.java  2022/6/4 23:47
 */
public class TestMain2 {
    public static void main(String[] args) {
        HashMap<String, Object> oldMap = new HashMap<String, Object>() {{
            put("a", "123");
        }};
        System.out.println(oldMap.hashCode());
        HashMap<String, Object> newMap = oldMap;
        System.out.println(newMap.hashCode());
        // 这里 newMap = null 表示把 newMap 引用地址从 oldMap => null，不影响 oldMap
        // newMap = null;

        /**
         *  同理如下：
         *  oldMap 初始化对象时会假设写入一个内存地址 001，这里oldMap = null，表示把 oldMap的引用指向 null
         *  而此时前面oldMap通过等号赋值传递给了newMap，newMap和odlMap 有相同的引用地址 001
         *  犹豫 oldMap = null，gc回收时会把 oldMap 释放掉
         *  原内存地址 001，如果没有被其他对象引用就会被GC时给释放掉，但是newMap 中也有001
         */
        // oldMap = null;


        // 而这里支持操作副本里面的值，oldMap 和 newMap ，都会跟着改变
        // newMap.remove("a");

        System.out.println(oldMap);
    }
}
