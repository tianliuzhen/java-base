package com.aaa.javabase.multithreading.countdownlatch;

/**
 * 参考：https://blog.csdn.net/anshilv/article/details/52398602
 *
 * 利用countDownLatch将异步多线程结果同步返回
 * @author liuzhen.tian
 * @version 1.0 MultiJobExecutors.java  2021/2/22 19:21
 */


import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


public class MultiJobExecutors {
    private CountDownLatch countDownLatch;
    private List<Task> tasks;
    private  static ExecutorService executor = null;

    public MultiJobExecutors(List<Task> tasks) {
        countDownLatch = new CountDownLatch(tasks.size());
        executor = Executors.newFixedThreadPool(tasks.size());
        this.tasks = tasks;
    }

    public void execute() throws ExecutionException, InterruptedException {
        if (tasks == null|| tasks.isEmpty()) {
            return;
        }
        ArrayList<Map> total = Lists.newArrayList();
        for (int i=0;i< tasks.size();i++) {
            Future<Map> submit = executor.submit(new Job(countDownLatch, tasks.get(i)));
            // 保存每次执行的值
            total.add(submit.get());
        }
        try {
            //等待所有线程结束
            countDownLatch.await(15, TimeUnit.SECONDS);
            //执行其他操作
            System.out.println("it's over");
            System.out.println("total.toString() = " + total.toString());
            //关闭线程池
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Job implements Callable<Map>{
        private CountDownLatch latch;
        private Task task;

        public Job(CountDownLatch latch, Task task) {
            this.latch = latch;
            this.task = task;
        }

        @Override
        public Map call() throws Exception {
            System.out.println(System.currentTimeMillis());
            //执行线程
            task.execute();
            //countDown自减
            latch.countDown();
            HashMap<Object, Object> map = new HashMap<>();
            map.put("str", task.str);
            return map;
        }
    }
    private static class Task{
        private String str;

        public Task(String str) {
            this.str = str;
        }

        public void execute(){
            System.out.println(str);
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task = new Task("I");
        Task task1 = new Task("love");
        Task task2 = new Task("you");
        Task task3 = new Task(",");
        Task task4 = new Task("its");
        Task task5 = new Task("not");
        Task task6 = new Task("true");

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(task);
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);
        tasks.add(task6);

        MultiJobExecutors multiJobExecutors = new MultiJobExecutors(tasks);
        multiJobExecutors.execute();
    }
}
