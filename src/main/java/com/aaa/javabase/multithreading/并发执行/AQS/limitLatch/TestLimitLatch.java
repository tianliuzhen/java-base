package com.aaa.javabase.multithreading.并发执行.AQS.limitLatch;

import org.apache.tomcat.util.threads.LimitLatch;
import org.springframework.util.StopWatch;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Tomcat正是基于LimitLatch，做请求并发控制
 *
 * @author liuzhen.tian
 * @version 1.0 TestLimitLatch.java  2024/6/22 12:17
 */
public class TestLimitLatch {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //  这里设置限制5个请求
        LimitLatch limitLatch = new LimitLatch(5);

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    // 这里同时会进来10个请求，但是因为limit=5，导致只能有5个请求执行，5个请求等待
                    limitLatch.countUpOrAwait();

                    // 因此这里会有5个请求同时执行
                    System.out.println("Thread.currentThread().getName() = "
                            + Thread.currentThread().getName()
                            + "-" + new Date());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        // ignore
                    }
                } catch (InterruptedException e) {
                    // ignore
                } finally {
                    // 这里必须要减的，否则limit无法回调
                    limitLatch.countDown();
                }
            });
        }


        stopWatch.stop();

        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());

        executorService.shutdown();

    }
}
