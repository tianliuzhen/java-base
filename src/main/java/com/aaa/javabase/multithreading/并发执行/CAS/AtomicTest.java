package com.aaa.javabase.multithreading.并发执行.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzhen.tian
 * @version 1.0 AtomicTest.java  2021/3/1 22:51
 */
public class AtomicTest {
    private static AtomicInteger index = new AtomicInteger(10);
    public static void main(String[] args) {
        new Thread(() -> {
            index.compareAndSet(10, 11);
            index.compareAndSet(11, 10);
            System.out.println(Thread.currentThread().getName()+
                    "：10->;11->;10");
        },"张三").start();

        new Thread(() ->  {
            try {
                TimeUnit.SECONDS.sleep(2);
                boolean isSuccess = index.compareAndSet(10, 12);
                System.out.println(Thread.currentThread().getName()+
                        "：index是预期的10嘛，"+isSuccess
                        +"   设置的新值是："+index.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"李四").start();
    }
}
