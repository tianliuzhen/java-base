package com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 InheritableThreadLocalTest1.java  2020/10/25 0:53
 */
public class InheritableThreadLocalTest1 {
    public static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
    public static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开启");
        threadLocal.set(1);

        executorService.submit(() -> {
            System.out.println("子线程读取本地变量：" + threadLocal.get());
            threadLocal.remove();
        });

        TimeUnit.SECONDS.sleep(1);

        threadLocal.set(2);

        executorService.submit(() -> {
            System.out.println("子线程读取本地变量：" + threadLocal.get());
            threadLocal.set(3);
            System.out.println("子线程读取本地变量：" + threadLocal.get());
            threadLocal.remove();
        });

    }
}

