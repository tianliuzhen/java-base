package com.aaa.javabase.multithreading.interrupt;

/**
 * @author liuzhen.tian
 * @version 1.0 MyThread.java  2020/9/3 10:38
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("i = " + i);
        }
        super.run();
    }

}
