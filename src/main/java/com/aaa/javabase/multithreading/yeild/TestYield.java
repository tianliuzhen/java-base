package com.aaa.javabase.multithreading.yeild;

/**
 * 测试线程让步
 * <p>
 * 每次结果可能不同
 * A begin = Thread-0
 * B begin = Thread-1
 * B over = Thread-1
 * B begin = Thread-2
 * B over = Thread-2
 * A over = Thread-0
 *
 * @author liuzhen.tian
 * @version 1.0 TestYeild.java  2022/5/24 21:30
 */
public class TestYield {

    /**
     * yield 方法并不保证当前线程会立即停止执行，它只是向调度器发出一个建议，让调度器可以安排其他线程执行。
     * 实际上，调度器是否立即选择另一个线程执行取决于调度策略和当前系统的线程状态。
     *
     * 不是每次都是这个输出结果：
     * <p>
     * A begin = Thread-0  （执行完 Thread.yield() 后，让B线程去执行）
     * B begin = Thread-1
     * B over = Thread-1
     * A over = Thread-0
     * <p>
     */
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("A begin = " + Thread.currentThread().getName());
            Thread.yield();
            System.out.println("A over = " + Thread.currentThread().getName());
        }).start();


        new Thread(() -> {
            System.out.println("B begin = " + Thread.currentThread().getName());
            System.out.println("B over = " + Thread.currentThread().getName());
        }).start();

    }

}
