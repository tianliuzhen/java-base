package com.aaa.javabase.multithreading.classlock;

/**
 * description: 对象锁
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/25
 */
public class ObjectLockThread implements Runnable  {
    public ObjectLockThread() {
    }

    @Override
    public void run() {
        method2();
    }
    public  synchronized void method2() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

}
