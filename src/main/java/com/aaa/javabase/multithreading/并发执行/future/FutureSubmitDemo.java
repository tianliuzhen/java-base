package com.aaa.javabase.multithreading.并发执行.future;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * 使用 executorService.invokeAll 去执行
 * @author liuzhen.tian
 * @version 1.0 FutureDemo.java  2021/2/24 20:53
 */
public class FutureSubmitDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long begin = System.currentTimeMillis();

        List<Callable<Boolean>> callableList = Lists.newArrayList();

        List<Future<Boolean>> submitFutures = Lists.newArrayList();
        for (long i = 1; i <= 3; i++) {
            long finalI = i;
            Future<Boolean> submit = executorService.submit(() -> {
                // 异常睡眠 1s 2s 3s ...
                TimeUnit.SECONDS.sleep(finalI);
                return false;
            });
            submitFutures.add(submit);
        }
        //注意：此时不会阻塞，要想等到所有的线程执行完之后，请调用get()
        for (Future<Boolean> submitFuture : submitFutures) {
            submitFuture.get();
        }

        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - begin) / 1000.0);
        System.out.println("end...");

    }
}
