package com.aaa.javabase.base.list.线程安全;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 顾名思义，它的意思就是在写操作的时候复制数组。再加一个写锁，读不加锁
 * 为了将读取的性能发挥到极致，在该类的使用过程中，
 * 读读操作和读写操作都不互斥。
 *
 * @author liuzhen.tian
 * @version 1.0 TestCopyOnWriteArrayList.java  2022/7/5 21:17
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new CopyOnWriteArrayList();
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
