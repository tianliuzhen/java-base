package com.aaa.javabase.multithreading.ThreadPriority;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class Thread1 extends Thread {
    public Thread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(this.getName()+"正在执行"+i);
        }
    }
}
