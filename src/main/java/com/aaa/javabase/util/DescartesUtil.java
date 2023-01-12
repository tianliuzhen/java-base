package com.aaa.javabase.util;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 笛卡尔集工具类
 *
 * @author liuzhen.tian
 * @version 1.0 DescartesUtil.java  2023/1/12 21:22
 */
public class DescartesUtil {


    public static void main(String[] args) {
        List<Long> a = Lists.newArrayList(1L, 2L, 3L);
        List<Long> b = Lists.newArrayList(4L);
        List<Long> c = Lists.newArrayList(7L, 8L);

        List<List<Long>> dimensionValue = Lists.newArrayList(a, b, c);

        List<List<Long>> result = Lists.newArrayList();
        descartes(dimensionValue, result, 0, new ArrayList<>());

        result.forEach(System.out::print);
    }


    /**
     * 笛卡尔积组合
     *
     * @param allValue    所有组合数据
     * @param lastResult  最终返回结果
     * @param index       下标
     * @param currentList 当前值
     * @param <T>         泛型
     */
    private static <T> void descartes(List<List<T>> allValue, List<List<T>> lastResult, int index, List<T> currentList) {
        // 根据下标取每种组合类型list值
        List<T> ts = allValue.get(index);

        if (index < allValue.size() - 1) {
            for (T t : ts) {
                ArrayList<T> arrayList = new ArrayList<>(currentList);
                arrayList.add(t);
                descartes(allValue, lastResult, index + 1, arrayList);
            }
        }

        // 所有组合数据最后一层
        if (index == allValue.size() - 1) {
            for (T t : ts) {
                // 重新创建一个最新的组合对象
                List<T> arrayList = new ArrayList<>(currentList);
                arrayList.add(t);
                lastResult.add(arrayList);
            }
        }
    }


}
