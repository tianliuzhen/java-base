package com.aaa.javabase.multithreading.threadlocal;

/**
 * ThreadLocal 的使用场景。（个人线程单独计数）
 *
 * @author liuzhen.tian
 * @version 1.0 ThreadLocalUnsafe.java  2023/11/19 18:39
 */
public class ThreadLocalUnsafe implements Runnable {

    public static ThreadLocal<Number> numberThreadLocal = new ThreadLocal<Number>();
    /**
     * 使用threadLocal的静态变量
     * 这里没使用静态变量，是每个线程单独自己的副本变量。
     * 如果使用了静态变量，是所有的线程是可见的
     */
    public Number number = new Number(0);

    @Override
    public void run() {
        //每个线程计数加一
        number.setNum(number.getNum() + 1);
        //将其存储到ThreadLocal中
        numberThreadLocal.set(number);
        //延时2ms
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出num值
        System.out.println("内存地址：" + numberThreadLocal.get() + "，" + Thread.currentThread().getName() + "=" + numberThreadLocal.get().getNum());
    }


    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            new Thread(new ThreadLocalUnsafe()).start();
        }
    }

    /**
     * 一个私有的类 Number
     */
    private static class Number {
        public Number(int num) {
            this.num = num;
        }

        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
