package com.aaa.javabase.multithreading.daemonThread;

/**
 * @author liuzhen.tian
 * @version 1.0 DaemonThreadTest2.java  2020/12/17 19:27
 */
import java.util.concurrent.TimeUnit;

public class DaemonThreadTest2 {
    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable(){
            @Override
            public void run()
            {
                Thread childThread = new Thread(new ChildrenThread());
                childThread.setDaemon(true);
                childThread.start();
                System.out.println("I'm main thread...");
            }
        });
        mainThread.start();

        Thread otherThread = new Thread(new Runnable(){
            @Override
            public void run()
            {
                while(true)
                {
                    System.out.println("I'm other user thread...");
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
        });
        otherThread.start();
    }

  static   class ChildrenThread implements Runnable
    {
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


