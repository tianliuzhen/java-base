package com.aaa.javabase.base.jdk8.sort.map;

import com.aaa.javabase.domain.Student;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestStreamMapSort.java  2020/12/10 11:50
 */
public class TestStreamMapSort {
    public static void main(String[] args) {
        // obj();

        consts();
    }

    private static void consts() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 3);
        map.put("c", 2);
        map.put("b", 6);
        System.out.println("-----排序前");
        System.out.println(map);

        System.out.println("-----按map key排序后");
        Map<String, Integer> treeMapKey = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(e-> treeMapKey.put(e.getKey(),e.getValue()));
        System.out.println(treeMapKey);

        System.out.println("-----按map value排序后");
        Map<String, Integer> treeMapValue = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(e-> treeMapValue.put(e.getKey(),e.getValue()));
        System.out.println(treeMapValue);

        System.out.println("-----按map value排序后(降序): ");
        // 这里必须指定具体参数 Map.Entry.<String, Integer> 否则无法降序
        Map<String, Integer> treeMapValueDesc = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(e-> treeMapValueDesc.put(e.getKey(),e.getValue()));
        System.out.println(treeMapValueDesc);

        // 或者另外一种写法，这里以 按值排序,个人感觉这种更好
        LinkedHashMap<String, Integer> collect = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new
                        ));
        System.out.println("-----按值排序新的写法");
        System.out.println(collect);

    //    使用TreeMap按键排序



    }


    private static void obj() {
        Map<String, Student> map = new HashMap<>();
        map.put("a", new Student("tom", 23));
        map.put("c", new Student("tom1", 13));
        map.put("b", new Student("tom2", 33));


    }
}
