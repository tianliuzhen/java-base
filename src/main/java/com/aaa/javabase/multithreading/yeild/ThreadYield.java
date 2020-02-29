package com.aaa.javabase.multithreading.yeild;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class ThreadYield extends Thread {
    public ThreadYield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 9; i++) {
            System.out.println(this.getName()+"正在执行"+i);
            System.out.println(Thread.currentThread().getName());
            if(i==5){
                Thread.yield();
            }
        }
    }

}
