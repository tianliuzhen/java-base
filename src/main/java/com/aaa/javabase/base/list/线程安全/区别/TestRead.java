package com.aaa.javabase.base.list.线程安全.区别;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 读的效率：
 * vector =  synchronizedList < copyOnWriteArrayList
 * <p>
 * vector: 180
 * synchronizedList: 190
 * copyOnWriteArrayList: 10
 *
 * @author liuzhen.tian
 * @version 1.0 TestRead.java  2022/7/5 21:26
 */
public class TestRead {
    public static void main(String[] args) {
        testVector();
        testSynchronizedList();
        testCopyOnWriteArrayList();
    }

    public static void testVector() {
        Vector<Integer> vector = new Vector<>();
        vector.add(0);
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            vector.get(0);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("vector: " + (time2 - time1));
    }

    public static void testSynchronizedList() {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        list.add(0);
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.get(0);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("synchronizedList: " + (time2 - time1));
    }

    public static void testCopyOnWriteArrayList() {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(0);
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.get(0);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("copyOnWriteArrayList: " + (time2 - time1));
    }
}
