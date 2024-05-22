package com.aaa.javabase.base.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestHeapOOM.java  2024/5/16 22:20
 */
public class TestHeapOOM {
    /**
     -Xms100m -Xmx100m -XX:+PrintGCDetails
     -XX:+HeapDumpOnOutOfMemoryError
     -XX:HeapDumpPath=./log/heapdump.hprof
     -XX:+PrintGCDateStamps
     -Xloggc:./log/gc-oomHeap.log
     */
    public static void main(String[] args) {
        List<byte[]> bytes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            byte[] byte1 = new byte[1024 * 1024 * 10];
            bytes.add(byte1);
        }
    }
}
