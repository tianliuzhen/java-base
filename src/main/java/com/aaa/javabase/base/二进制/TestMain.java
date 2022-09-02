package com.aaa.javabase.base.二进制;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/9/1 20:14
 */
public class TestMain {
    public static void main(String[] args) {
        int CAPACITY = (1 << 29) - 1;
        int RUNNING = -1 << 29;
        int SHUTDOWN = 0 << 29;
        int STOP = 1 << 29;
        int TIDYING = 2 << 29;
        int TERMINATED = 3 << 29;
        System.out.println("CAPACITY   " + fullLengthTo32(CAPACITY) + "    " + CAPACITY);
        System.out.println("~CAPACITY  " + fullLengthTo32(~CAPACITY) + "    " + ~CAPACITY);
        System.out.println("RUNNING    " + fullLengthTo32(RUNNING) + "    " + RUNNING);
        System.out.println("SHUTDOWN   " + fullLengthTo32(SHUTDOWN) + "    " + SHUTDOWN);
        System.out.println("STOP       " + fullLengthTo32(STOP) + "    " + STOP);
        System.out.println("TIDYING    " + fullLengthTo32(TIDYING) + "    " + TIDYING);
        System.out.println("TERMINATED " + fullLengthTo32(TERMINATED) + "    " + TERMINATED);
        System.out.println("==========");
        System.out.println(5 & CAPACITY);
        System.out.println(-536870911 & CAPACITY);
        System.out.println(-536870912 & CAPACITY);
        System.out.println(-536870913 & CAPACITY);
        System.out.println(-2 & CAPACITY);
        System.out.println(-1 & CAPACITY);
        System.out.println(0 & CAPACITY);

        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        int i = workerCountOf(ctl.get());
        int i2 = workerCountOf(ctl.get() + 2);
        int runStateOf1 = (ctl.get() + 2) & ~CAPACITY;
        int runStateOf2 = (STOP ) & ~CAPACITY;
        System.out.println();
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    private static int workerCountOf(int c) {
        return c & ((1 << 29) - 1);
    }

    //Java Integer.toBinaryString 打印字符串，二进制补码默认为 32位，如果数字为负数则正好打印32位，如果为正数，则会以第一个1开始打印，如果为0就是0
//所以这里提供一个小工具方法，可以统一将二进制32位补码打印出来
    private static String fullLengthTo32(int arg) {
        String str = Integer.toBinaryString(arg);
        int gap = 32 - str.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < gap; i++) {
            result.append("0");
        }
        return result.append(str).toString();
    }
}
