package com.aaa.javabase.multithreading.createtype;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class TestClient {
    private Thread1 a2;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程执行開始!");
        //集成 Thread
//        Thread1 a = new Thread1("a1");
//        Thread1 a2 = new Thread1("a2");
//        a.start();
//        a2.start();
        //实现 Runnable
        Thread t1 = new Thread(new Thread2("b1"));
        t1.start();
//        t1.yield();
        Thread t2 = new Thread(new Thread2("b2"));
        t2.start();
        System.out.println(Thread.currentThread().getName()+"主线程执行结束!");
    }
}
