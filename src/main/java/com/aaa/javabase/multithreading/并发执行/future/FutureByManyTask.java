package com.aaa.javabase.multithreading.并发执行.future;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author liuzhen.tian
 * @version 1.0 FutrueByManyTask.java  2021/3/1 22:59
 */
public class FutureByManyTask {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static <T> List<T> doManyTask(List<Callable<T>> callables){

        //定义总的future
        List<Future<T>> futureList = Lists.newArrayList();
        //最终执行完的结果
        List<T> resultList= Lists.newArrayList();

        for (Callable<T> callable : callables) {
            Future<T> future = executorService.submit(callable);
            futureList.add(future);
        }
        for (Future<T> future : futureList) {
            T res = null;
            try {
                //超时返回
                res = future.get(3, TimeUnit.SECONDS);
            } catch (InterruptedException | TimeoutException | ExecutionException e) {
                e.printStackTrace();
            }
            if (res !=null) {
                resultList.add(res);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<Callable<Integer>> list = Lists.newArrayList();
        list.add(() -> 1);
        list.add(() -> 2);
        list.add(() -> 3);
        List<Integer> res = doManyTask(list);
        System.out.println(res);
    }
}
