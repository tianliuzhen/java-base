package com.aaa.javabase.multithreading.classlock;

/**
 * description: 类锁
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/25
 */
public class ClassLockThread implements Runnable {
    //测试类锁
    private static String obj = "";
    public void method1() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        method1();
    }
}
