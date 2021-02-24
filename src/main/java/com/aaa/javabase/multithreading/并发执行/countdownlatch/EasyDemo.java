package com.aaa.javabase.multithreading.并发执行.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * countDown 一个简单的demo
 * @author liuzhen.tian
 * @version 1.0 EasyDemo.java  2021/2/24 20:40
 */
public class EasyDemo {


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CountDownLatch countDownLatch = new CountDownLatch(2);

        System.out.println("start...");
        long begin = System.currentTimeMillis();

        executorService.submit(() -> {
            System.out.println( "test：1");
            TimeUnit.SECONDS.sleep(2);
            countDownLatch.countDown();
            return true;
        });
        executorService.submit(() -> {
            System.out.println( "test：2");
            TimeUnit.SECONDS.sleep(2);
            countDownLatch.countDown();
            return true;
        });

        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("执行时间" + (end - begin) / 1000.0);
        System.out.println("end...");
    }
}
