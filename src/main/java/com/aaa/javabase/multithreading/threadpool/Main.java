package com.aaa.javabase.multithreading.threadpool;

import java.util.Date;
import java.util.concurrent.*;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/29
 */
public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Main Thread------: Starting at: "+ new Date());
            //创建线程池
            BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
               ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10,
                       200, TimeUnit.MILLISECONDS,
                       queue,
                       new ThreadPoolExecutor.DiscardPolicy());
            for (int i = 0; i < 10; i++) {
                //将线程放入到线程池里面
                 executor.submit(new Handler("Hello" + i));
            }
            // Java线程池的完整构造函数


            /**
             *  当线程池调用该方法时,线程池的状态则立刻变成SHUTDOWN状态,以后不能再往线程池中添加任何任务，
             *  否则将会抛出RejectedExecutionException异常。
             *  但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出。
            * */
            executor.shutdown();
            System.out.println("Main Thread--------: Finished  at: "+ new Date());

        } catch (Exception e) {

        }
    }

}

class ThreadDemo extends Thread {

    private String threadName;

    ThreadDemo(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 1; i > 0; i--) {
                System.out.println("线程: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("线程： " + threadName + " interrupted.");
        }
        System.out.println("线程：" + threadName + " exiting.");
    }


}
