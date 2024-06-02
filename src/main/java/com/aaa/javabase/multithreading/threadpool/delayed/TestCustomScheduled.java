package com.aaa.javabase.multithreading.threadpool.delayed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 TestCustomScheduled.java  2024/6/2 14:59
 */
public class TestCustomScheduled {
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");

    /**
     * 不推荐：自定义的带有界队列调度
     */
    private final static CustomScheduledThreadPoolExecutor boundedExecutor = new CustomScheduledThreadPoolExecutor(
            2,
            new ThreadPoolExecutor.AbortPolicy(),
            10, 10, TimeUnit.SECONDS);

    public static void main(String[] args) {
        doOneTask();
    }

    private static void doOneTask() {
        Date date = new Date();
        System.out.println("main开始执行... " + format.format(new Date()));
        for (int i = 0; i <10; i++) {
            doScheduled();
        }
        System.out.println("main执行结束... " + format.format(new Date()));
    }


    private static void doScheduled() {
        boundedExecutor.schedule(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "开始执行... " + format.format(new Date()));
                // Thread.sleep(2000);
                System.out.println("执行结束... " + format.format(new Date()));
            } catch (Exception e) {

            }
        }, 5, TimeUnit.SECONDS);
    }

}
