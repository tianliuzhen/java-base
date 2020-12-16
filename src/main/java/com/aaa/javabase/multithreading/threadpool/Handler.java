package com.aaa.javabase.multithreading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/29
 */
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能概要：缓冲线程池实例-execute运行
 *
 * @author linbingwen
 * @since  2016年5月24日
 */
class Handler implements Runnable {
    private String name;
    public Handler(String name) {
        this.name = "thread"+name;
    }
    @Override
    public void run() {
        // System.out.println( name +" Start. Time = "+new Date());
        processCommand();
        // System.out.println( name +" End. Time = "+new Date());
    }
    private void processCommand() {
        try {
            System.out.println("当前线程名："+Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString(){
        return this.name;
    }
}
