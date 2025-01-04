package com.aaa.javabase.base.jdk8.stream.reduce;

import java.util.stream.Stream;

/**
 * @author liuzhen.tian
 * @version 1.0 TestStreamReduce.java  2022/5/26 21:28
 */
public class TestStreamReduce {
    public static void main(String[] args) {
        // 找出最大数
        Integer student = Stream.of(1, 2, 6, 4, 5, 3).reduce((v1, v2) -> v1 > v2 ? v1 : v2).get();
        System.out.println(student);

        // 找出最大数 - 带默认值
        Integer student2 = Stream.of(1, 2, 6, 4, 5, 3).reduce(65, (v1, v2) -> v1 > v2 ? v1 : v2);
        System.out.println(student2);

        // 求累计值
        Integer result = Stream.of(2, 2, 2, 2, 2).reduce((v1, v2) -> v1 + v2).get();
        System.out.println(result);

        // 求累计值 - 带默认值
        Integer resultDefault = Stream.of(2, 1, 2, 7, 2).reduce(3, (v1, v2) -> {
            System.out.println("v1 = " + v1 + "," + "v2 = " + v2);
            return v1 + v2;
        });
        System.out.println(resultDefault);

        Integer studentId = Stream.of(1, 2, 3, 4, 5, 6).parallel().reduce(0,
                (sum, v) -> sum + v,
                (s1, s2) -> s1 * s2);
        System.out.println(studentId);
    }
}
