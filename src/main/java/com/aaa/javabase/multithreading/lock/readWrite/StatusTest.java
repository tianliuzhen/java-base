package com.aaa.javabase.multithreading.lock.readWrite;

/**
 * @author liuzhen.tian
 * @version 1.0 StatusTest.java  2024/6/23 20:05
 */
public class StatusTest {
    static final int SHARED_SHIFT = 16;
    // 读锁的偏移量
    static final int SHARED_UNIT = (1 << SHARED_SHIFT);
    // 最多允许共享锁持有量：throw new Error("Maximum lock count exceeded");
    static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
    // 65536-1 ，这样它的二级制都是11
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    public static void main(String[] args) {
        testSharedCount();
        testExclusiveCount();
    }

    private static void testExclusiveCount() {
        // 低16位表示独占锁
        int c = 0;
        c = c + 1; // 00 0000 0000 0000 0001
        c = c + 1; // 00 0000 0000 0000 0010
        c = c + 1; // 00 0000 0000 0000 0011
        int i = exclusiveCount(c);
    }

    private static void testSharedCount() {
        // 高16位表示独占锁
        int c = 0;
        c = c + SHARED_UNIT; // 01 0000 0000 0000 0000
        c = c + SHARED_UNIT; // 10 0000 0000 0000 0000
        c = c + SHARED_UNIT; // 11 0000 0000 0000 0000
        int i = sharedCount(c);
    }

    static int sharedCount(int c) {
        /*
         * 01 0000 0000 0000 0000
         * 10 0000 0000 0000 0000
         * 11 0000 0000 0000 0000
         * >>>
         * 00 0000 0000 0000 0001 = 1
         * 00 0000 0000 0000 0010 = 2
         * 00 0000 0000 0000 0011 = 3
         */
        return c >>> SHARED_SHIFT;
    }

    static int exclusiveCount(int c) {
        /*
         * 0000 0000 0000 0001
         * 0000 0000 0000 0010
         * 0000 0000 0000 0011
         * &
         * 1111 1111 1111 1111
         * =
         * 0000 0000 0000 0001 = 1
         * 0000 0000 0000 0010 = 2
         * 0000 0000 0000 0011 = 3
         *
         */
        return c & EXCLUSIVE_MASK;
    }
}
