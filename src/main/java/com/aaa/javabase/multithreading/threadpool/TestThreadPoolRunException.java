package com.aaa.javabase.multithreading.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程池，执行任务时异常的问题
 *
 * @author liuzhen.tian
 * @version 1.0 TestThreadPoolRunException.java  2022/9/28 18:46
 */
public class TestThreadPoolRunException {

    /**
     * threadFactory 这个参数只对  threadPoolExecutor.execute 生效
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1,
            1,
            5,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5),
            new ThreadFactoryBuilder()
                    .setNameFormat("自定义线程 %d")
                    .setUncaughtExceptionHandler((t, e) -> System.out.println("UncaughtExceptionHandler捕获到：" + t.getName() + "发生异常" + e.getMessage()))
                    .build());

    public static void main(String[] args) {
        /**
         * 如果执行的任务异常，当前线程无法复用，会创建一个新的线程
         */
        testExecute();

        /**
         * 如果执行的任务异常，当前线程可以复用
         * 原理：java.util.concurrent.FutureTask#run() 进行了try扑捉
         */
        // testSubmit();
    }

    private static void testExecute() {
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                int a = 1 / 0;
            });
        }
    }

    private static void testSubmit() {
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(() -> {
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                int a = 1 / 0;
            });
        }
    }
}
