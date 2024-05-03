package com.aaa.javabase.base.堆;

/**
 * @author liuzhen.tian
 * @version 1.0 HeapSpaceTest.java  2024/4/24 23:24
 */
public class HeapSpaceTest {
    public static void main(String[] args) {
        //返回Java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        // 返回Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms : " + initialMemory + "M");
        System.out.println("-Xmx :"+ maxMemory +"M");
    }
}
