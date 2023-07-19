package com.aaa.javabase.base.gc;

/**
 * JVM 提供了许多不同类型的 GC 日志参数。以下是一些常用的参数：
 * <p>
 * -XX:+PrintGC: 开启 GC 日志输出
 * -XX:+PrintGCDetails: 输出详细 GC 日志
 * -XX:+PrintGCTimeStamps: 输出 GC 时间戳
 * -XX:+PrintGCDateStamps: 输出 GC 日期时间戳
 * -XX:+PrintHeapAtGC: 打印堆信息
 * -Xloggc:filename: 输出 GC 日志到指定文件名
 *
 * @author liuzhen.tian
 * @version 1.0 TestGc.java  2023/7/19 22:00
 */
public class TestGc {
    /**
     * https://mp.weixin.qq.com/s/OCLs3ZFFMQeDIGNdAFWT2g
     *
     * @param args
     */
    public static void main(String[] args) {
        demo1();  // 不回收
        // demo2(); // 回收 （手动赋值 null）
        // demo3(); // 回收 （同方法不同作用域,有新的索引）
        // demo4(); // 回收 （同方法不同作用域,五新的索引）
        // demo5(); // 回收 （方法执行完，无引用）
        // demo6();    // 不回收（方法执行完，有引用）

        /**
         * new byte[64 * 1024 * 1024]; 表示创建64m的数组
         * 不回收：
         *[GC (System.gc())  73351K->67410K(249344K), 0.0020038 secs]
         *[Full GC (System.gc())  67410K->67259K(249344K), 0.0084777 secs]
         * 回收：
         * [GC (System.gc())  73351K->67442K(249344K), 0.0024311 secs]
         * [Full GC (System.gc())  67442K->67259K(249344K), 0.0087904 secs]
         *
         * 总结：
         * 1、方法块中的对象，每次gc都会回收掉
         * 2、方法体中对象，可以手动指定null，或者在作用域外，任意执行一行，否则也不会回收
         */
    }

    private static void demo1() {
        byte[] arr = new byte[64 * 1024 * 1024];
        System.out.println(arr.length / 1024);

        System.gc();
    }

    private static void demo2() {
        byte[] arr = new byte[64 * 1024 * 1024];
        System.out.println(arr.length / 1024);
        arr = null;
        System.gc();
    }


    private static void demo3() {
        if (true) {
            byte[] arr = new byte[64 * 1024 * 1024];
            System.out.println(arr.length / 1024);
        }
        int a = 1;
        System.gc();
    }

    private static void demo4() {
        if (true) {
            byte[] arr = new byte[64 * 1024 * 1024];
            System.out.println(arr.length / 1024);
        }
        // int a = 1; 如果没有这一步，还是不会回收
        System.gc();
    }


    private static void demo5() {
        extracted();
        // int a = 1;
        System.gc();
    }

    private static void demo6() {
        byte[] extracted = extracted();
        // int a = 1;
        System.gc();
        System.out.println(extracted.length / 1024);
    }


    private static byte[] extracted() {
        byte[] arr = new byte[64 * 1024 * 1024];
        System.out.println(arr.length / 1024);
        return arr;
    }
}
