package com.aaa.javabase.multithreading.threadpool.prioritized;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/23
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PriorityDemo {

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                10,
                Long.MAX_VALUE, /* timeout */
                TimeUnit.NANOSECONDS,
                new PriorityBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        PrioritizedRunnable p1 = new PrioritizedRunnable(1, "name1-1");

        PrioritizedRunnable p2 = new PrioritizedRunnable(33, "name4-2");
        PrioritizedRunnable p3 = new PrioritizedRunnable(6, "name4-2");
        PrioritizedRunnable p4 = new PrioritizedRunnable(9, "name5-3");

        // 这个线程先填满线程池，后面线程进来就会按顺序执行
        executor.execute(p1);

        // 按顺序执行
        executor.execute(p2);
        executor.execute(p3);
        executor.execute(p4);

        log.info("submit 5 Runnable");
        // Thread.sleep(30*1000);
        executor.shutdown();
        log.info("done!");
    }
}

