package com.aaa.javabase.multithreading.并发执行.AQS.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 PhaserExample.java  2022/3/20 20:08
 */
public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                // 动态注册任务
                phaser.register();

                try {
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + "  is working");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + "   work finished");

                //等待其他线程完成工作
                phaser.arriveAndAwaitAdvance();
            }).start();

        }

        System.out.println("the last .......................");
    }
}
