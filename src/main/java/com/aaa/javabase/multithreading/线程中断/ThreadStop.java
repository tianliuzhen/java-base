package com.aaa.javabase.multithreading.线程中断;

import org.springframework.util.StopWatch;

/**
 * @author liuzhen.tian
 * @version 1.0 ThreadStop.java  2024/1/18 21:45
 */
public class ThreadStop {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        testStop(); // 使用线程的stop()来中断线程，比较暴力直接就能关了
        // testInterrupt(); // 使用线程的interrupt()来中断线程,（需要人为的介入判断interrupted才能进行捕捉）

        // testInterrupt3(); // Thread.sleep() 遇上 interrupt()，直接中断
        // testInterrupt4();  // Thread.sleep() 遇上 interrupt()，可以再次抛出 InterruptedException 进行传递

        stopWatch.stop();
        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());
    }

    public static void testInterrupt() {
        Thread thread1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    System.out.println("thread1线程被中断");
                    break;
                }
                System.out.println("thread1循环中...");
            }
        });

        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
    }


    public static void testStop() {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1启动了");
            while (true) {
                System.out.println("thread1循环中...");
            }
        });
        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("准备关闭thread1线程");
        thread1.stop();

        System.out.println("主线程停止");
    }

    public static void testInterrupt3() {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1线程启动了。。。");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
    }

    public static void testInterrupt4() {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1线程启动了。。。");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    System.out.println("thread1程序终止");
                    break;
                }
            }
        });

        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
    }


}
