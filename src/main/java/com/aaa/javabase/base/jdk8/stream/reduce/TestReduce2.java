package com.aaa.javabase.base.jdk8.stream.reduce;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Set;

/**
 * @author liuzhen.tian
 * @version 1.0 TestReduce2.java  2022/5/26 21:52
 */
public class TestReduce2 {
    public static void main(String[] args) {
        HashMap<Integer, Set<String>> map = Maps.newHashMap();
        map.put(1, Sets.newHashSet("a", "b", "c", "j"));
        map.put(2, Sets.newHashSet("d", "b", "c"));
        map.put(3, Sets.newHashSet("d", "b", "e", "c"));

        // 求map value总的集合交集，且不重复
        Set<String> res = map.values().stream().reduce((x, y) -> {
            x.retainAll(y);
            return x;
        }).orElse(Sets.newHashSet());

        System.out.println(map);
        System.out.println(res);


    }
}
