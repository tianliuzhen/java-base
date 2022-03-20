package com.aaa.javabase.multithreading.并发执行.AQS.phaser;

/**
 * @author liuzhen.tian
 * @version 1.0 PhaserExample2.java  2022/3/20 20:21
 */

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample2 {
    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        //初始化5个parties
        Phaser phaser = new Phaser(3);
        for (int i = 0; i < 3; i++) {
            new Athlete(phaser, i).start();
        }
        System.out.println("the last .......................");

    }

    //创建运动员类
    private static class Athlete extends Thread {
        private Phaser phaser;
        private int no;//运动员编号

        public Athlete(Phaser phaser, int no) {
            this.phaser = phaser;
            this.no = no;
        }

        @Override
        public void run() {
            try {
                System.out.println(no + ": 当前处于第：" + phaser.getPhase() + "阶段");
                System.out.println(no + ": start running");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(no + ": end running");
                //等待其他运动员完成跑步
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + ": 当前处于第：" + phaser.getPhase() + "阶段");
                System.out.println(no + ": start bicycle");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(no + ": end bicycle");
                //等待其他运动员完成骑行
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + ": 当前处于第：" + phaser.getPhase() + "阶段");
                System.out.println(no + ": start long jump");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(no + ": end long jump");
                //等待其他运动员完成跳远
                phaser.arriveAndAwaitAdvance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
