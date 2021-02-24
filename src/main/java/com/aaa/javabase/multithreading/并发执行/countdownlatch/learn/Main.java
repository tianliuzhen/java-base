package com.aaa.javabase.multithreading.并发执行.countdownlatch.learn;


import com.aaa.javabase.multithreading.并发执行.countdownlatch.learn.thread.RemoteBankThread;
import com.aaa.javabase.multithreading.并发执行.countdownlatch.learn.thread.RemoteLoanThread;
import com.aaa.javabase.multithreading.并发执行.countdownlatch.learn.thread.RemotePassportThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 参考：https://blog.csdn.net/qq_35264464/article/details/79604414
 */
public class Main {

    private static ThreadPoolExecutor executor;

    public static void main(String[] args) {
        final int poolSize = 3;
        final int maxPoolSize = poolSize * 4;

        executor = new ThreadPoolExecutor(poolSize,maxPoolSize,
                1,TimeUnit.MINUTES, new LinkedBlockingQueue<>(),new MyThreadFactory());

        boolean result = check();

        executor.shutdownNow();

        System.out.println(result ? "验证成功" : "验证失败");
    }

    private static boolean check(){
        int taskNumber = 3;
        int uid = 1;

        CountDownLatch latch = new CountDownLatch(taskNumber);
        List<FutureTask<Boolean>> tasks = new ArrayList<>();
        tasks.add(new CheckFutureTask(new RemoteBankThread(uid),latch,taskNumber));
        tasks.add(new CheckFutureTask(new RemoteLoanThread(uid),latch,taskNumber));
        tasks.add(new CheckFutureTask(new RemotePassportThread(uid),latch,taskNumber));

        for(FutureTask<Boolean> task : tasks){
            executor.execute(task);
        }
        try {
            latch.await();

            for(FutureTask<Boolean> task : tasks){
                task.cancel(true);
            }

            for(FutureTask<Boolean> task : tasks){
                if(!task.get()){
                    return false;
                }
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
