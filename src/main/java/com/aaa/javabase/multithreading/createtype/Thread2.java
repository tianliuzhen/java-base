package com.aaa.javabase.multithreading.createtype;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class Thread2 implements Runnable {
    private String name;
    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println(name + "执行  :  " + i+"当前线程 : "+Thread.currentThread().getName());
        }
    }

    public Thread2(String name) {
        this.name = name;
    }
}
