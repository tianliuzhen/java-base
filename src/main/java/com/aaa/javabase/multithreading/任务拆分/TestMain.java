package com.aaa.javabase.multithreading.任务拆分;

import com.aaa.javabase.util.ThreadPoolUtil;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2024/4/4 13:09
 */
public class TestMain {

    /**
     * 拆分的任务总数
     */
    public static final int totalTask = 3;

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println();

        /*
         * 需求：有100个鸡蛋，需要分别放在1，2，3三个篮子中
         * 要求：多线程
         *
         * 重新理解一下,本质上属于数据分片的需求，可以借助取模来实现均匀分配
         * 核心点：取模运算
         * i % totalTask == taskId
         *      1%3 = 1
         *      2%3 = 2
         *      3%3 = 0
         *      4%3 = 1
         *      5%3 = 2
         *      6%3 = 0
         *      ...
         *      100%3 = 1
         *
         *
         */
        List<String> eggList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            eggList.add("鸡蛋：" + i);
        }

        // 三个篮子对应着三个任务
        List<Future<Map<String, List<String>>>> futures = new ArrayList<>();
        for (int i = 0; i < totalTask; i++) {
            futures.add(ThreadPoolUtil.common_pool.submit(new Task(i, eggList)));
        }
        Map<String, List<String>> res = new HashMap<>();
        for (Future<Map<String, List<String>>> future : futures) {
            res.putAll(future.get());
        }

        List<String> collect = res.entrySet().stream()
                .flatMap(e -> e.getValue().stream())
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
        System.out.println();
    }

    @Data
    public static class Task implements Callable<Map<String, List<String>>> {

        private int taskId;
        private List<String> eggList;

        public Task(int taskId, List<String> eggList) {
            this.taskId = taskId;
            this.eggList = eggList;
        }

        private Task() {
        }

        @Override
        public Map<String, List<String>> call() throws Exception {
            HashMap<String, List<String>> res = new HashMap<>();
            List<String> data = new ArrayList<>();
            for (int i = 0; i < eggList.size(); i++) {
                if (i % totalTask == taskId) {
                    data.add(eggList.get(i));
                }
            }
            res.put(taskId + "", data);
            return res;
        }
    }
}
