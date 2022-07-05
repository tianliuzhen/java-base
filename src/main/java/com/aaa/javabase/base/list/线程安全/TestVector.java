package com.aaa.javabase.base.list.线程安全;

import java.util.List;
import java.util.Vector;

/**
 * Vector类实现了可扩展的对象数组，并且它是线程安全的。
 * 它和ArrayList在常用方法的实现上很相似，不同的只是它采用了同步关键词synchronized修饰方法。
 * ArrayList中的add方法：
 * @see Vector#add(java.lang.Object)
 *
 * @author liuzhen.tian
 * @version 1.0 TestVector.java  2022/7/5 21:07
 */
public class TestVector {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new Vector<>();
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
