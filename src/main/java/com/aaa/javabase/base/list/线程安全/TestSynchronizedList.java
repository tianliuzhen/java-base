package com.aaa.javabase.base.list.线程安全;

import java.util.*;

/**
 * 使用这种方法我们可以获得线程安全的List容器，它和Vector的区别在于它采用了同步代码块实现线程间的同步
 *
 * @author liuzhen.tian
 * @version 1.0 TestSynchronizedList.java  2022/7/5 21:14
 */
public class TestSynchronizedList {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }
        Thread.sleep(500);
        System.out.println(list.size());
    }
}
