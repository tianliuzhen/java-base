package com.aaa.javabase.multithreading.并发执行.AQS.phaser;

/**
 * @author liuzhen.tian
 * @version 1.0 PhaserExample3.java  2022/3/20 20:27
 */

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample3 {
    private static Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws InterruptedException {
        //初始化5个parties
        Phaser phaser = new Phaser(5);

        //只有当全部线程通过时才会进入下一阶段，从0开始
        System.out.println("当前阶段数："+phaser.getPhase());

        //添加一个parties
        phaser.register();
        System.out.println("当前Parties数："+phaser.getRegisteredParties());
        //添加多个parties
        phaser.bulkRegister(4);
        System.out.println("当前Parties数："+phaser.getRegisteredParties());

        new Thread(new Runnable() {
            @Override
            public void run() {
                //到达并等待其他线程到达
                phaser.arriveAndAwaitAdvance();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //到达后注销该parties，不等待其他线程
                phaser.arriveAndDeregister();
                System.out.println("go on");
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("当前Parties数："+phaser.getRegisteredParties());
        System.out.println("当前到达数："+phaser.getArrivedParties());
        System.out.println("当前未达数："+phaser.getUnarrivedParties());

        //何时会停止，只有当parties中的数量为0时或者调用forceTermination方法就会停止了，我们也可以重写phaser中的onAdvance，给他返回true就会使这个phaser停止了
        System.out.println("phaser是否结束："+phaser.isTerminated());
        phaser.forceTermination();
        System.out.println("phaser是否结束："+phaser.isTerminated());
    }

}
