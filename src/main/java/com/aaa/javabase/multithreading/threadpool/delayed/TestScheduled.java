package com.aaa.javabase.multithreading.threadpool.delayed;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 TestScheduled.java  2023/5/12 23:00
 */
public class TestScheduled {

    // private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(6);
    private final static ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(
            6,
            new ThreadFactoryBuilder().setNameFormat("common-scheduler-pool-%d").build());

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");

    public static void main(String[] args) {
        // 延迟不循环任务schedule方法
        for (int i = 0; i < 3; i++) {
            doScheduled();
        }


        // 延迟且循环sheduleAtFixedRate方法
        // doSheduleAtFixedRate();

        // todo 定时间间隔执行，如果间隔时间小于业务执行时间，间隔时间无效。
        // doSheduleWithFixedDelay();
    }

    private static void doScheduled() {
        scheduler.schedule(() -> {
            try {
                System.out.println("开始执行... " + format.format(new Date()));
                Thread.sleep(2000);
                System.out.println("执行结束... " + format.format(new Date()));

                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

            } catch (Exception e) {

            }
        }, 5, TimeUnit.SECONDS);
    }

    /**
     * 以固定比率执行
     * </p>
     * 如果业务执行时间 > period，那么间隔时间 = 业务执行时间
     * 如果业务执行时间 < period，那么间隔时间 = period
     * </p>
     * 开始执行... 2023-05-12 23:30:007
     * 执行结束... 2023-05-12 23:30:009
     * 开始执行... 2023-05-12 23:30:009
     * 执行结束... 2023-05-12 23:30:011
     */
    private static void doSheduleAtFixedRate() {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("开始执行... " + format.format(new Date()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行结束... " + format.format(new Date()));
            // throw new RuntimeException(); 如果出现异常则中断
        }, 0, 3, TimeUnit.SECONDS);
    }

    /**
     * 以固定的延迟来执行
     * </p>
     * 如果业务执行时间 > delay，那么间隔时间 = 业务执行时间 + delay
     * 如果业务执行时间 < delay，那么也是间隔时间 =  业务执行时间 + delay
     * </p>
     * 开始执行... 2023-05-12 23:34:029
     * 执行结束... 2023-05-12 23:34:031
     * 开始执行... 2023-05-12 23:34:032
     * 执行结束... 2023-05-12 23:34:034
     */
    private static void doSheduleWithFixedDelay() {
        scheduler.scheduleWithFixedDelay(() -> {
            try {
                System.out.println("开始执行... " + format.format(new Date()));
                Thread.sleep(2000);
                System.out.println("执行结束... " + format.format(new Date()));
            } catch (Exception e) {
            }
        }, 0, 3, TimeUnit.SECONDS);
    }
}
