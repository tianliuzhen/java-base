package com.aaa.javabase.base;

/**
 * @author liuzhen.tian
 * @version 1.0 ThreadInterruptTest.java  2025/7/25 23:37
 */
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.err.println("stop");
                }
                System.err.println("1");
            }
        });

        thread.start();

        Thread.sleep(2);

        thread.interrupt();
    }
}
