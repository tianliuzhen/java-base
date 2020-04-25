package com.aaa.javabase.multithreading.classlock;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/25
 */
public class MyThread2 implements Runnable  {
    public MyThread2() {
    }

    @Override
    public void run() {
        method2();
    }
    public static synchronized void method2() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

}
