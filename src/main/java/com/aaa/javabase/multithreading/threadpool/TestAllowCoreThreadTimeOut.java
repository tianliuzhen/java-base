package com.aaa.javabase.multithreading.threadpool;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试几个问题
 *  1、拒绝策略生效条件：如：最大线程池(4) + 有界队列(5) = 9，并发超过9或者>=10,才会执行拒绝策略
 *  2、线程池核心线程数，线程使用完是否会销毁：【默认不会销毁】
 *  3、线程存活时间，指的的核心线程还是非核心线程：【非核心线程】
 *  4、线程池初始化时，核心线程数：【初始为0】
 *
 * @author liuzhen.tian
 * @version 1.0 TestAllowCoreThreadTimeOut.java  2022/2/14 20:28
 */
public class TestAllowCoreThreadTimeOut {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            2,
            4,
            5,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5));

    static {
        // 执行完任务，回收核心线程数，默认只会根据定义的存活时间回收，非核心线程数
        pool.allowCoreThreadTimeOut(true);
        System.err.println("初始化-- 池中的当前线程数 = " + pool.getPoolSize());
        System.err.println("初始化-- 池中的活跃线程数 = " + pool.getActiveCount());
        // 当前线程数：线程池内存在的线程
        // 活跃线程数：线程池当前处理任务的线程

    }

    @SneakyThrows
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(9);
        /**
         * 这里定义并发9个线程，当前定义线程池的，最大线程池(4) + 有界队列(5) = 9
         * 如果超过9，就会执行线程池的拒绝策略
         */
        for (int i = 0; i < 9; i++) {
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        System.out.println("池中的当前线程数 = " + pool.getPoolSize());
        System.out.println("池中的活跃线程数 = " + pool.getActiveCount());

        // 未执行完任务前，检测线程池
        while (pool.getActiveCount() > 0) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("=======================");
            System.out.println("池中的当前线程数 = " + pool.getPoolSize());
            System.out.println("池中的活跃线程数 = " + pool.getActiveCount());
        }

        // 阻塞
        countDownLatch.await();

        System.out.println();
        System.out.println("分隔符---------------线程池任务处理结束--------------------");
        System.out.println();

        // 执行完任务后，检测线程池
        while (pool.getPoolSize() > 0) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("=======================");
            System.out.println("池中的当前线程数 = " + pool.getPoolSize());
            System.out.println("池中的活跃线程数 = " + pool.getActiveCount());
        }

    }
}
