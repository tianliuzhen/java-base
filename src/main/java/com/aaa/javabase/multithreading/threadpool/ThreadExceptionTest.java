package com.aaa.javabase.multithreading.threadpool;

import java.util.concurrent.*;

/**
 * @author liuzhen.tian
 * @version 1.0 ThreadExceptionTest.java  2021/2/24 19:59
 */
public class ThreadExceptionTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Boolean> future = executor.submit(new CallableTask());
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //必须不能忘，否则会一致卡在那里，但是切记，在项目中，万不可直接调用 shutdown(),否则会造成拒绝异常。
        executor.shutdown();
    }
}

   class  CallableTask implements Callable<Boolean> {
    public Boolean call() throws Exception {
        int num = 3/0;
        return false;
    }

}
