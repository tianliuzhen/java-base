package com.aaa.javabase.base.集合;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 TestHashMap.java  2025/1/4 18:52
 */
public class TestConcurrentModificationException {
    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("Apple", "red");
        hashMap.put("Orange", "yellow");

        // 遍历期间for循环中，put新数据（非更新）、删除数据,会导致  ConcurrentModificationException
        // forAddFail(hashMap);
        // forDelFail(hashMap);
        forDelSuccess(hashMap);
        System.out.println(hashMap);
    }

    private static void forDelSuccess(HashMap<Object, Object> hashMap) {
        Iterator<Map.Entry<Object, Object>> iterator = hashMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Object, Object> next = iterator.next();
            System.out.println("next = " + next);
            iterator.remove();
        }
    }

    private static void forAddFail(HashMap<Object, Object> hashMap) {
        for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());

            hashMap.put("Apple", "yellow");
        }
    }

    private static void forDelFail(HashMap<Object, Object> hashMap) {
        for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());

            hashMap.remove(entry.getKey());
        }
    }
}
