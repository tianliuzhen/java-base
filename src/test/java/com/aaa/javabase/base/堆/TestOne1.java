package com.aaa.javabase.base.堆;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestOne1.java  2024/4/23 21:03
 */
public class TestOne1 {
    /**
     * 查看新生代和老年代占比：jinfo -flag NewRatio 37496
     * 查看新生代eden和survivor占比：jinfo -flag SurvivorRatio  37496
     * -XX:SurvivorRatio=8
     * -XX:-UseAdaptiveSizePolicy
     *
     * -Xms6m
     * -XX:InitialHeapSize=6m
     * -Xmx80m
     * -XX:MaxHeapSize=80m
     *
     */
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> bytes = new ArrayList<>();
        while (true) {
            Thread.sleep(10000);
            bytes.add(new byte[1024 * 1024 * 10]);
        }
    }
}
