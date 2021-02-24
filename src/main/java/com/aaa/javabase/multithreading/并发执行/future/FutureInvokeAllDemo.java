package com.aaa.javabase.multithreading.并发执行.future;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * 使用 executorService.invokeAll 去执行
 * @author liuzhen.tian
 * @version 1.0 FutureDemo.java  2021/2/24 20:53
 */
public class FutureInvokeAllDemo {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long begin = System.currentTimeMillis();

        List<Callable<Boolean>> callableList = Lists.newArrayList();

        callableList.add(() -> {
            TimeUnit.SECONDS.sleep(2);
            return false;
        });

        callableList.add(() -> {
            TimeUnit.SECONDS.sleep(3);
            return false;
        });

        /**
         * 参考：https://blog.csdn.net/woaiqianzhige/article/details/88878088
         * ExecutorService.invokeAll()方法接收一个list<Callable>,当全部任务都执行完成后返回List<Future<T>> futures
         * 并且这种方式是能保证请求和返回值的一一对应关系，顺序是一致的
         *
         * 这里使用  invoke有风险：  因为invokeAll方法上会调用future.get方法，且是堵塞的，如果任务被丢弃了，则会永远堵塞在这里**
         *
         */
        List<Future<Boolean>> futures = executorService.invokeAll(callableList);

        //这里和执行  executorService.submit 不太一样，此时 futures 的结果是全部执行完后的结果，
        //不须调用 get() 使其阻塞，才能 使线程全部执行。

        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - begin) / 1000.0);
        System.out.println("end...");

    }
}
