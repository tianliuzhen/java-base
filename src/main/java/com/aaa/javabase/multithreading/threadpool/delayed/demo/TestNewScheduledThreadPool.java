package com.aaa.javabase.multithreading.threadpool.delayed.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 TestNewScheduledThreadPool.java  2021/12/17 21:34
 */
@Slf4j
public class TestNewScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName() + "延时1s后，每5s执行一次工作任务--- >");
            }
        };
        log.error("开始执行....");
        executor.scheduleAtFixedRate(runnable, 1, 5, TimeUnit.SECONDS);
    }
}
