package com.aaa.javabase.multithreading.并发执行.AQS.countDownLatch;

import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestCountLatch.java  2024/6/22 12:19
 */
public class TestCountLatch {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        stopWatch.stop();

        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());
    }
}
