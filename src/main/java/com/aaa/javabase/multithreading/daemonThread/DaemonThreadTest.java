package com.aaa.javabase.multithreading.daemonThread;

/**
 * 参考：https://blog.csdn.net/u013256816/article/details/50392298
 * @author liuzhen.tian
 * @version 1.0 DaemonThreadTest.java  2020/12/17 18:48
 */

import java.util.concurrent.TimeUnit;

public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable(){
            @Override
            public void run() {
                Thread childThread = new Thread(new ChildrenThread());
                // Daemon = true 为守护线程
                childThread.setDaemon(true);
                childThread.start();
                System.out.println("I'm main thread...");
            }
        });
        mainThread.start();
    }
    static  class ChildrenThread implements Runnable {
        @Override
        public void run()
        {
            while(true)
            {
                System.out.println("I'm child thread..");
                try
                {
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

