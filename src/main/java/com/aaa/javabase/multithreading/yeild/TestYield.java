package com.aaa.javabase.multithreading.yeild;

/**
 * 测试线程让步
 * <p>
 * 每次结果可能不同
 * A begin = Thread-0
 * B begin = Thread-1
 * B over = Thread-1
 * B begin = Thread-2
 * B over = Thread-2
 * A over = Thread-0
 *
 * @author liuzhen.tian
 * @version 1.0 TestYeild.java  2022/5/24 21:30
 */
public class TestYield {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("A begin = " + Thread.currentThread().getName());
            Thread.yield();
            System.out.println("A over = " + Thread.currentThread().getName());
        }).start();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println("B begin = " + Thread.currentThread().getName());
                System.out.println("B over = " + Thread.currentThread().getName());
            }).start();
        }
    }

}
