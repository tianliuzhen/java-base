package com.aaa.javabase.multithreading.threadlocal;

/**
 * 初始化：ThreadLocal 并设定初始化
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @date 2020/4/11
 */
public class TestThreadLocal {

    //线程本地存储变量
    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        //启动三个线程
            for (int i = 0; i <3; i++) {
                new Thread(TestThreadLocal::add10ByThreadLocal).start();
            }
    }

    /**
     * 线程本地存储变量加 5
     */
    private static void add10ByThreadLocal() {
        for (int i = 0; i <5; i++) {
            Integer n = THREAD_LOCAL_NUM.get();
            n += 1;
            THREAD_LOCAL_NUM.set(n);
            System.out.println(Thread.currentThread().getName() + " : ThreadLocal num=" + n);
        }
    }

}
