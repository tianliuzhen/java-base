package com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal;

import com.aaa.javabase.base.jdk8.stream.groupby.Stu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * MyInheritableThreadLocal 重写  InheritableThreadLocal 的 childValue 实现深克隆
 * <p>
 * 父线程读取的变量跟子线程读取的变量，是两个的不是一个
 *
 * @author liuzhen.tian
 * @version 1.0 InheritableThreadLocalTest12.java  2020/10/26 0:06
 */
public class InheritableThreadLocalTest3 {
    public static ThreadLocal<Stu> threadLocal = new MyInheritableThreadLocal<>();
    public static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开启");
        threadLocal.set(new Stu("aaa", 1L));

        executorService.submit(() -> {
            System.out.println("子线程读取本地变量：" + threadLocal.get());
            threadLocal.get().setAge(55L);
            System.out.println("子线程读取本地变量：" + threadLocal.get());

        });

        TimeUnit.SECONDS.sleep(1);

        System.out.println("主线程读取本地变量：" + threadLocal.get());
        threadLocal.get().setAge(99L);

        executorService.submit(() -> {
            System.out.println("子线程读取本地变量：" + threadLocal.get());
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程读取本地变量：" + threadLocal.get());
    }
}
