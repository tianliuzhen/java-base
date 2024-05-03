package com.aaa.javabase.base.堆;

/**
 * @author liuzhen.tian
 * @version 1.0 StackErrorTest.java  2024/4/23 22:21
 */
public class StackErrorTest {
    static int count = 0;
    /**
     * 有俩种设置方式
     * -Xss100k
     * -XX:ThreadStackSize=100k  这个不生效
     */
    public static void main(String[] args) {
        try {
            count++;
            main(args);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("递归次数 = " + count);
        }
    }
}
