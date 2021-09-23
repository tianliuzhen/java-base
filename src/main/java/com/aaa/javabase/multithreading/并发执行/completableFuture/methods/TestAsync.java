package com.aaa.javabase.multithreading.并发执行.completableFuture.methods;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Async后缀结尾的区别
 *
 * @author liuzhen.tian
 * @version 1.0 TestAsync.java  2021/9/23 21:07
 */
@Slf4j
public class TestAsync {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();

        log.info("complete begin ....");
        future.complete(1);
        log.info("complete end ....");

        // 1、whenComplete  的执行线程就是   future.complete(1); 执行的线程
        future.whenComplete((a, e) -> {
            try {
                log.info("等待一秒 ....");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).join();

        // 2、whenCompleteAsync(BiConsumer)  的执行线程是内置的   ForkJoinPool
        future.whenCompleteAsync((a, e) -> {
            try {
                log.info("等待一秒 ....");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).join();

        // 3、whenCompleteAsync(BiConsumer,Executor)  的执行线程是自定义的   Executor
        future.whenCompleteAsync((a, e) -> {
            try {
                log.info("等待一秒 ....");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }, Executors.newFixedThreadPool(10)).join();

        // 打印日志如下
        /**
         * [main] INFO com.aaa.javabase.multithreading.并发执行.completableFuture.methods.TestAsync - complete begin ....
         * [main] INFO com.aaa.javabase.multithreading.并发执行.completableFuture.methods.TestAsync - complete end ....
         * [main] INFO com.aaa.javabase.multithreading.并发执行.completableFuture.methods.TestAsync - 等待一秒 ....
         * [ForkJoinPool.commonPool-worker-1] INFO com.aaa.javabase.multithreading.并发执行.completableFuture.methods.TestAsync - 等待一秒 ....
         * [pool-1-thread-1] INFO com.aaa.javabase.multithreading.并发执行.completableFuture.methods.TestAsync - 等待一秒 ....
         */
    }
}
