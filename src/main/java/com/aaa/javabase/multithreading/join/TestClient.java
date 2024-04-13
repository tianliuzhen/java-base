package com.aaa.javabase.multithreading.join;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class TestClient {
    /**
     * join 方法主要用于线程（Thread）的同步。它的作用是让当前线程等待直到被join的线程完成其执行。
     * 如下面的demo
     * tom线程 sleep 1000ms
     * cat线程 sleep 500ms
     * 如果让 cat.join(); 执行。那么最终的结果就是
     * <p>
     * 主线程开始：main
     * cat执行  :  0当前线程 : Thread-1
     * cat执行  :  1当前线程 : Thread-1
     * tom执行  :  0当前线程 : Thread-0
     * cat执行  :  2当前线程 : Thread-1
     * tom执行  :  1当前线程 : Thread-0
     * cat执行  :  3当前线程 : Thread-1
     * cat执行  :  4当前线程 : Thread-1
     * 主线程结束：main
     * tom执行  :  2当前线程 : Thread-0
     * tom执行  :  3当前线程 : Thread-0
     * tom执行  :  4当前线程 : Thread-0
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始：" + Thread.currentThread().getName());
        Thread tom = new Thread(new Thread1("tom"));
        Thread cat = new Thread(new Thread1("cat"));
        tom.start();
        // tom.join();
        cat.start();
        cat.join();
        System.out.println("主线程结束：" + Thread.currentThread().getName());

        /**
         * 未试用用 join 之前 结果如下：
         * 主线程开始：main
         * 主线程结束：main
         * tom执行  :  0当前线程 : Thread-0
         * tom执行  :  1当前线程 : Thread-0
         * cat执行  :  0当前线程 : Thread-1
         * tom执行  :  2当前线程 : Thread-0
         * cat执行  :  1当前线程 : Thread-1
         * tom执行  :  3当前线程 : Thread-0
         * cat执行  :  2当前线程 : Thread-1
         * tom执行  :  4当前线程 : Thread-0
         * cat执行  :  3当前线程 : Thread-1
         * cat执行  :  4当前线程 : Thread-1
         *
         * */
    }
}
