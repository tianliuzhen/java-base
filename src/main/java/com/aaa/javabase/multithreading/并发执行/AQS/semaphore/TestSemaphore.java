package com.aaa.javabase.multithreading.并发执行.AQS.semaphore;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author liuzhen.tian
 * @version 1.0 TestSemaphore.java  2022/3/20 17:56
 */
public class TestSemaphore {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    System.out.println(LocalDateTime.now() + "-" + Thread.currentThread().getName() + "处理数据....前......");
                    semaphore.acquire();
                    System.err.println(LocalDateTime.now() + "-" + Thread.currentThread().getName() + "处理数据....中......");
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        executor.shutdown();

    }
}
