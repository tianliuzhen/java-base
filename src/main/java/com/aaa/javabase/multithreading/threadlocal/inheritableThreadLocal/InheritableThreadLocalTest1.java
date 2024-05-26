package com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal;

import java.util.concurrent.*;

/**
 * @author liuzhen.tian
 * @version 1.0 InheritableThreadLocalTest1.java  2020/10/25 0:53
 */
public class InheritableThreadLocalTest1 {
    public static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    // 把 executorService 换成这个就不会存在下面问题
    // final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 5,
    //         1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    /**
     * 下面所有的业务测试，都是属于内存泄漏的情况（每次使用完没有 clear ThreadLocal）
     * 因为 Executors.newFixedThreadPool(1)，创建的线程都是常驻线程，不会销毁。
     * 这就会导致一次父线程 threadLocal.set(2); 之后，
     * 因为子线程已经创建，再次执行时 parent.inheritableThreadLocals 还是第一次 new Thread()是，从父类copy的
     * 所以不会再次执行new Thread()，
     * 所以第二次 executorService.submit 里面的第一次输出 是null
     */
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

        /*
         *  因为executorService
         */
        executorService.submit(() -> {
            System.out.println("子线程读取本地变量：" + threadLocal.get());
            threadLocal.set(3);
            System.out.println("子线程读取本地变量：" + threadLocal.get());
            threadLocal.remove();
        });

    }
}

