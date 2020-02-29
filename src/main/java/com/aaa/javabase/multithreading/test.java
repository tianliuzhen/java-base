package com.aaa.javabase.multithreading;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class test {
    public static void main(String[] args) {
        int cpuNums = Runtime.getRuntime().availableProcessors();  //获取当前
        System.out.println(cpuNums);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable run");
            }
        } ) {
            @Override
            public void run() {
//                super.run(); //添加这一行
                System.out.println("Thread run");
            }
        }.start();
    }
}
