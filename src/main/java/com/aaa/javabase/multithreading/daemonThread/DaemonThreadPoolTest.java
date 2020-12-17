package com.aaa.javabase.multithreading.daemonThread;

/**
 * jdk 设置的线程默认是守护线程
 *     如果需要修改线程池创建线程不是用户线程，需要重写ThreadFactory
 * @author liuzhen.tian
 * @version 1.0 DaemonThreadPoolTest.java  2020/12/17 19:37
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DaemonThreadPoolTest {
    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable(){
            @Override
            public void run()
            {
                ExecutorService exec = Executors.newCachedThreadPool(new MyThreadFactory("守护线程"));
                Thread childThread = new Thread(new ClildThread());
                // childThread.setDaemon(true);
                exec.execute(childThread);
                exec.shutdown();
                System.out.println("I'm main thread...");
            }
        });
        mainThread.start();
    }

    static class MyThreadFactory implements ThreadFactory {

        private final String groupName;
        private AtomicInteger nextId = new AtomicInteger(1);

        public MyThreadFactory(String groupName) {
            this.groupName = "ThreadFactoryMain -" + groupName + "-worker-";
        }

        @Override
        public Thread newThread(Runnable r) {
            String threadName = groupName + nextId.incrementAndGet();
            Thread thread = new Thread(null,r,threadName,0);
                thread.setDaemon(true);
            if (thread.getPriority() != Thread.NORM_PRIORITY){
                thread.setPriority(Thread.NORM_PRIORITY);
            }

            return thread;
        }

    }

    static class ClildThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("I'm child thread..");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
