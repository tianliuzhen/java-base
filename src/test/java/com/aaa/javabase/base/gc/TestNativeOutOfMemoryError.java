package com.aaa.javabase.base.gc;

/**
 * @author liuzhen.tian
 * @version 1.0 TestNativeOutOfMemoryError.java  2024/5/27 21:48
 */
public class TestNativeOutOfMemoryError {

    /**
     *  -Xmx10m -Xms10m
     *  -Xss 设置每个线程栈的大小
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0;; i++) {
            System.out.println("i = " + i);
            Thread.sleep(1);
            new Thread(()->{
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // CountDownLatch countDownLatch = new CountDownLatch(1);
                // try {
                //     countDownLatch.await();
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
            }).start();
        }
    }

}
