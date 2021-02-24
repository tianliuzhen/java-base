package com.aaa.javabase.multithreading.多线程调试;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class test {
    public static void main(String[] args) {
        // 获取当前cpu 核数
        int cpuNums = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNums);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("床前明月光");
            }
        },"床前明月光" ).start();

        new Thread(() -> {
            System.out.println("疑是地上霜");

        },"疑是地上霜").start();

        System.out.println("举头望明月");
        System.out.println("低头思故乡");

    }
}
