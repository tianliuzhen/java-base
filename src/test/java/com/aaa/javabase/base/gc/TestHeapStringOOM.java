package com.aaa.javabase.base.gc;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author liuzhen.tian
 * @version 1.0 TestHeapOOM.java  2024/5/16 22:20
 */
public class TestHeapStringOOM {
    /**
     -Xms10m -Xmx10m -XX:+PrintGCDetails
     -XX:+HeapDumpOnOutOfMemoryError
     -XX:HeapDumpPath=./log/heapdump.hprof
     -XX:+PrintGCDateStamps
     -Xloggc:./log/gc-string-oomHeap.log

     -XX:-UseGCOverheadLimit
     */
    public static void main(String[] args) {
        // test1();
        test2();
    }

    private static void test1() {
        ArrayList<String> objects = new ArrayList<>();
        while (true) {
            objects.add(UUID.randomUUID().toString().intern());
        }
    }

    private static void test2() {
        String objects = "";
        while (true) {
            objects += UUID.randomUUID().toString();

        }
    }
}
