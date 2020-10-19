package com.aaa.javabase.multithreading.concurrent;

import java.util.Arrays;
import java.util.List;

/**
 * 问题： 无法统计多线程 最后执行的时间
 * @author liuzhen.tian
 * @version 1.0 Tets.java  2020/10/19 18:36
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        Long start = System.currentTimeMillis()/1000;
        for (Integer num : list) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }).start();
        }
    }
}
