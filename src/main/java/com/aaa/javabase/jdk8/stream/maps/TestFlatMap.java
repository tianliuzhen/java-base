package com.aaa.javabase.jdk8.stream.maps;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestFlatMap.java  2020/11/2 17:10
 */
public class TestFlatMap {
    public static void main(String[] args) throws Exception{
        // testFlatMapV1();
        testFlatMapV2();

    }

    //降维
    // 给 定 单 词 列 表[“Hello”,“World”] ，要返回列表 [“H”,“e”,“l”, “o”,“W”,“r”,“d”] 。
    // 使用map方法，代码如下：
    private static void testFlatMapV1() {
        //在java 8 Stream中，flatMap方法是一个维度升降的方法
        List<String> words = Arrays.asList("Hello", "World");
        List<String> collect = words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }
    // 升维
    // 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
    private static void testFlatMapV2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        // flatMap升维度
        List<int[]> pairs = numbers1.stream().flatMap(x -> numbers2.stream().map(y -> new int[] { x, y }))
                .collect(Collectors.toList());
        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }
    }
}
