package com.aaa.javabase.base;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author liuzhen.tian
 * @version 1.0 TestArrayListSort.java  2026/2/28 22:28
 */
public class TestArrayListSort {
    /**
     * 测试基本数据类型的排序
     */
    @Test
    public void testBasicTypeSort() {
        System.out.println("=== 基本数据类型排序 ===");

        // Integer 排序
        ArrayList<Integer> numbers = Lists.newArrayList(9,8, 3, 5, 7, 2);
        System.out.println("原始数组: " + numbers);

        // 升序排序
        numbers.sort( null);
        System.out.println("升序排序后: " + numbers);

        // 降序排序
        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("降序排序后: " + numbers);
    }
}
