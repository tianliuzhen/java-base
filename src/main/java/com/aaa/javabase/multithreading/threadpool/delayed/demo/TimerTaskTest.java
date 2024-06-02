package com.aaa.javabase.multithreading.threadpool.delayed.demo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author liuzhen.tian
 * @version 1.0 TimerTaskTest.java  2024/6/2 14:08
 */
public class TimerTaskTest {
    public static void main(String[] args) {
        System.out.println(new Date());
        for (int i = 0; i < 10; i++) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println(new Date());
                    System.out.println("Task executed");
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 5000);    // 在5秒内执行task
        }
        System.out.println(new Date());
    }
}
