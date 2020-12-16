package com.aaa.javabase.multithreading.threadpool;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/29
 */
public class Main {

    public static void main(String[] args) {
        Executors.newFixedThreadPool(1);
        try {
            System.out.println("Main Thread------: Starting at: "+ new Date());
            //创建线程池
               ThreadPoolExecutor executor = new ThreadPoolExecutor(
                       3,
                       10,
                       200,
                       TimeUnit.MILLISECONDS,
                       new ArrayBlockingQueue<>(512),
                       new MyThreadFactory("--MyThreadFactory--"),
                       new ThreadPoolExecutor.DiscardPolicy());
            for (int i = 0; i < 10; i++) {
                //将线程放入到线程池里面
                //提交方式1
                Future<?> future = executor.submit(new Handler("Hello" + i));
                System.out.println("future.isDone() = "+future.isDone());//判断任务是否已经完成，如果完成则返回true
                System.out.println("future.get() = " + future.get());
                System.out.println("future.isDone() = "+future.isDone());//判断任务是否已经完成，如果没完成则返回false，
                //提交方式2
//                executor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
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
  static class MyThreadFactory implements ThreadFactory{

      private final String groupName;
      private AtomicInteger nextId = new AtomicInteger(1);

      public MyThreadFactory(String groupName) {
          this.groupName = "ThreadFactoryMain -" + groupName + "-worker-";
      }

      @Override
      public Thread newThread(Runnable r) {
          String threadName = groupName + nextId.incrementAndGet();
          Thread thread = new Thread(null,r,threadName,0);
          return thread;
      }
  }

    static class ThreadDemo extends Thread {

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

}
