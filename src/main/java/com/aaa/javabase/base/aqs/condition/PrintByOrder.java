package com.aaa.javabase.base.aqs.condition;

import lombok.SneakyThrows;

/**
 * @author liuzhen.tian
 * @version 1.0 TestCondition.java  2022/9/11 10:53
 */
public class PrintByOrder {

    public int state = 1;

    @SneakyThrows
    public synchronized void print1() {
        while (state != 1) {
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " :print1...");
        state++;
        notifyAll();
    }

    @SneakyThrows
    public synchronized void print2() {
        while (state != 2) {
            wait();
        }
        System.out.println(Thread.currentThread().getName() + "：print2...");
        state++;
        notifyAll();
    }

    @SneakyThrows
    public synchronized void print3() {
        while (state != 3) {
            wait();
        }
        System.out.println(Thread.currentThread().getName() + "：print3...");
        state = 1;
        notifyAll();

    }

    public static void main(String[] args) {
        PrintByOrder printByOrder = new PrintByOrder();
        new Thread(() -> {
            while (true) {
                printByOrder.print1();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                printByOrder.print2();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                printByOrder.print3();
            }
        }).start();
    }
}
