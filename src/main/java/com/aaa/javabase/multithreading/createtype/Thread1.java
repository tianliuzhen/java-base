package com.aaa.javabase.multithreading.createtype;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class Thread1 extends Thread {

    private String name;
    public Thread1(String name) {
        this.name=name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "执行  :  " + i+"线程名字");
        }
    }
}
