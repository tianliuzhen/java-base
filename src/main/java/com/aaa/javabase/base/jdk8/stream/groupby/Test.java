package com.aaa.javabase.base.jdk8.stream.groupby;


import java.util.*;
import java.util.stream.Collectors;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/5/23
 */
public class Test {
    public static void main(String[] args) {
        List<Stu> list = new ArrayList<>();
        list.add(new Stu("a", 1L));
        list.add(new Stu("b", 2L));
        list.add(new Stu("b", 1L));
        // 1. stream 分组写法是先转成 map 格式
        Map<String, LongSummaryStatistics> map = list.stream().collect(Collectors.groupingBy(Stu::getName, Collectors.summarizingLong(Stu::getAge)));
        list.forEach((System.out::print));
        System.out.println();
        // 2. 再把分组后的map 转成想要的list
        List<Stu> collect = map.entrySet().stream().map(e -> new Stu(e.getKey(), e.getValue().getSum())).collect(Collectors.toList());
        collect.forEach((System.out::print));
    }
}
