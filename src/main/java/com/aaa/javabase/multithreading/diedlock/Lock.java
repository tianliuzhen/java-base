package com.aaa.javabase.multithreading.diedlock;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/25
 */
public class Lock {
    private boolean isLocked = false;
    public synchronized void lock() throws InterruptedException{
        while(isLocked){
            System.out.println(Thread.currentThread().getName()+"：获取到锁");
            wait();
        }
        isLocked = true;
    }
    public synchronized void unlock(){
        isLocked = false;
        notify();
        System.out.println(Thread.currentThread().getName()+"：释放锁");
    }
}
