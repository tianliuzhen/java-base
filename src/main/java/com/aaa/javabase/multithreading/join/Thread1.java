package com.aaa.javabase.multithreading.join;

import java.util.concurrent.TimeUnit;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class Thread1 implements Runnable {
    private String name;
    @Override
    public void run() {
        for (int i = 0; i <5 ; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
            }
            System.out.println(name + "执行  :  " + i+"当前线程 : "+Thread.currentThread().getName());
        }
    }

    public Thread1(String name) {
        this.name = name;
    }
}
