package com.aaa.javabase.multithreading.synchronizeds;

/**
 * description:  测试  volatile 线程的可见性
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/27
 */
public class VolatileTest implements Runnable {

  volatile   static boolean flag = true;

    @Override
    public void run() {
        while (flag) {
        }
        System.out.println("end......");
    }

    public static void main(String[] args) {
        new Thread(new VolatileTest()).start();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
        System.out.println("end main......");
    }
}
