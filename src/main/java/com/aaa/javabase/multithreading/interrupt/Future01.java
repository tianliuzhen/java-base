package com.aaa.javabase.multithreading.interrupt;

/**
 * @author liuzhen.tian
 * @version 1.0 Future01.java  2021/1/24 11:06
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Future01 {


    public static void main(String[] args) {
        ExecutorService eService = Executors.newFixedThreadPool(5);
        Future<?> future= eService.submit(new RunFuture());
        try {
            Thread.sleep(1000);
            //mayInterruptIfRunning设成false话，不允许在线程运行时中断，
            // 设成true的话就允许,并且程序未执行结束就中断。
            future.cancel(false);
            System.out.println("haha---------------");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

class RunFuture implements Runnable{
    int i=0;
    @Override
    public void run() {
        while (i < 1000000 && !Thread.currentThread().isInterrupted()/**/) {
            System.out.println("i++:" + i);
            i++;
        }

    }

}
