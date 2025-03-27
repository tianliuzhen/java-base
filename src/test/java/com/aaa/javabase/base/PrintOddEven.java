package com.aaa.javabase.base;

/**
 * @author liuzhen.tian
 * @version 1.0 PrintOddEven.java  2025/3/27 21:28
 */
public class PrintOddEven {
    private static final Object object = new Object();

    private static volatile int count = 1;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (true) {
                sleep();
                synchronized (object) {

                    if (count % 2 == 1) {
                        System.out.println(Thread.currentThread() + "-" + count);
                        count++;
                        object.notifyAll();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(1);
                }
            }
        }, "奇数");
        oddThread.start();

        Thread even = new Thread(() -> {
            while (true) {
                sleep();
                synchronized (object) {
                    if (count % 2 == 0) {
                        System.out.println(Thread.currentThread() + "-" + count);
                        count++;
                        object.notifyAll();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(2);
                }
            }

        }, "偶数");
        even.start();


    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
