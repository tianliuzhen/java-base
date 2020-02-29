package com.aaa.javabase.multithreading.wait;

/**
 * description:这是一道比較经典的面试题，题目要求例如以下：
 * 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同一时候执行，
 * 交替打印10次ABC。这个问题用Object的wait()，notify()就能够非常方便的解决。代码例如以下：
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/28
 */
public class MyThreadPrinter2 implements Runnable {

    private String name;
    private Object prev;
    private Object self;

    private MyThreadPrinter2(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            // 第一个线程                         第二个线程                                     第三个线程
            // 等待上一个线程  c 锁释放            //   a 等待上一个线程  a 锁释放              //    b 等待上一个线程  b 锁释放
            synchronized (prev) {
            // a  给自个加锁                       //   b   给自个加锁                         //    c 给自个加锁
                synchronized (self) {
                    System.out.print(name);
                    System.out.print(count+" ");
                    count--;
                    // 唤醒下一个线程
                    self.notify();
                }
                try {
                    // eg：让出当前A线程  留给 B线程执行
                    prev.wait();
            //让当前线程 c 锁等待                          // a 锁等待                                   // b 锁等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);
        MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);
        MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);


        new Thread(pa).start();
        Thread.sleep(100);  //确保按顺序A、B、C执行
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);

        /**
         *
         */
    }
}
