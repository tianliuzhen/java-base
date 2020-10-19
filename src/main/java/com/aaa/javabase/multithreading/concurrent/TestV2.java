package com.aaa.javabase.multithreading.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池解决 执行时间统计的时间
 * @author liuzhen.tian
 * @version 1.0 TestV2.java  2020/10/19 18:53
 */
public class TestV2 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 使用线程池自带方法 executor.isTerminated() ，统计线程池执行时间
        // test1();

        // 2. 使用CountDownLatch ，统计线程池执行时间
        test2();
    }

    private static void test2() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " done.");
            });
        }
        System.out.println("tasks Started ");
        countDownLatch.await();
        System.out.println("tasks Over ");
        System.out.println("all tasks have been done in " + (System.currentTimeMillis() - start) + "ms");
        /**
         * 切记 这里，线程池要最后关闭，如果放在   countDownLatch.await(); 前面，会导致提前终止阻塞的线程。
         * 从而使   countDownLatch.await();  无效
         */
        executor.shutdown();
    }

    public static void test1() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " done.");
            });
        }
        executor.shutdown();
        /**
         *  线程池中提供了监控线程池运行的一些方法，这里通过线程池的 isTerminated() 方法不断检测，
         *  线程池中的任务是否都执行完成了，来获取所有任务结束时间。
         */
        while (!executor.isTerminated()) {
            // pass
        }
        System.out.println("all tasks have been done in " + (System.currentTimeMillis() - start) + "ms");
    }
}
