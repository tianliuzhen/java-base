package com.aaa.javabase.base.list.线程安全.区别;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 三种方式的性能比较
 * vector > synchronizedList = copyOnWriteArrayList
 * <p>
 * vector: 550
 * synchronizedList: 2991
 * copyOnWriteArrayList: 2869
 *
 * @author liuzhen.tian
 * @version 1.0 TestWrite.java  2022/7/5 21:19
 */
public class TestWrite {
    public static void main(String[] args) {
        testVector();
        testSynchronizedList();
        testCopyOnWriteArrayList();
    }

    public static void testVector() {
        Vector vector = new Vector();
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            vector.add(i);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("vector: " + (time2 - time1));
    }

    public static void testSynchronizedList() {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("synchronizedList: " + (time2 - time1));
    }

    public static void testCopyOnWriteArrayList() {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("copyOnWriteArrayList: " + (time2 - time1));
    }
}
