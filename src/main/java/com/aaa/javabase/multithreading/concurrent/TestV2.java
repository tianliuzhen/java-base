package com.aaa.javabase.multithreading.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestV2.java  2020/10/19 18:53
 */
public class TestV2 {
    public static void main(String[] args) {

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
        while (!executor.isTerminated()) {
            // pass
        }
        System.out.println("all tasks have been done in " + (System.currentTimeMillis() - start) + "ms");
    }
}
