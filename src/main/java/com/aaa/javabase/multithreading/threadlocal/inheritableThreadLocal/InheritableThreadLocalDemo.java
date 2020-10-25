package com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal;

/**
 * @author liuzhen.tian
 * @version 1.0 InheritableThreadLocalDemo.java  2020/10/24 23:20
 */
public class InheritableThreadLocalDemo {
    // private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 这个类能让子线程继承父线程中已经设置的ThreadLocal值。
     */
    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("mainThread");
        System.out.println("value:"+threadLocal.get());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String value = threadLocal.get();
                System.out.println("value:"+value);
            }
        });
        thread.start();
    }

}
