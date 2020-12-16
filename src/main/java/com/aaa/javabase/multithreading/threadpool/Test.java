package com.aaa.javabase.multithreading.threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2020/12/16 19:05
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i=0; i<5; i++) {
            Callable<String> c = new Task();
            MyFutureTask ft = new MyFutureTask(c);
            executor.submit(ft);
        }
        executor.shutdown();
    }

}

class MyFutureTask extends FutureTask<String> {

    public MyFutureTask(Callable<String> callable) {
        super(callable);
    }

    @Override
    protected void done() {
        try {
            System.out.println(get() + " 线程执行完毕！~");
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        Random rand = new Random();
        TimeUnit.SECONDS.sleep(rand.nextInt(12));
        return Thread.currentThread().getName();
    }
}
