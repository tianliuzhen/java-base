package com.aaa.javabase.base.线程交替打印;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzhen.tian
 * @version 1.0 PrintOddEven.java  2025/3/27 21:28
 */
public class PrintOddEvenV2 {
    private static final Object object = new Object();

    private static volatile int count = 1;

    public static void main(String[] args) {
        // 创建信号量：奇数初始可获取，偶数初始不可获取
        Semaphore oddSemaphore = new Semaphore(1);
        Semaphore evenSemaphore = new Semaphore(1);

        AtomicInteger counter = new AtomicInteger(1);


        Thread oddThread = new Thread(() -> {
            try {
                while (true) {
                    oddSemaphore.acquire(); // 获取奇数信号量
                    if (counter.get() > 100) break;

                    if (counter.get() % 2 == 1) {
                        System.out.println("奇数: " + counter.getAndIncrement());
                        evenSemaphore.release(); // 释放偶数信号量
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "奇数");
        oddThread.start();

        Thread even = new Thread(() -> {
            try {
                while (true) {
                    evenSemaphore.acquire(); // 获取偶数信号量
                    if (counter.get() > 100) break;



                    if (counter.get() % 2 == 0) {
                        System.out.println("偶数: " + counter.getAndIncrement());
                        oddSemaphore.release(); // 释放奇数信号量

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "偶数");
        even.start();


    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
