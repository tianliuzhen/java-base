package com.aaa.javabase.util;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @author liuzhen.tian
 * @version 1.0 BloomFilterUtil.java  2022/3/25 21:31
 */
public class BloomFilterUtil {
    public static void main(String[] args) {
        filterInteger();
    }

    public static void filterInteger() {
        // 创建布隆过滤器对象 ，初始化布隆过滤器：预计元素为1000 0000,误差率为千分之3
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                10000000,
                0.001);
        // 判断指定元素是否存在
        System.out.println(filter.mightContain(10));
        System.out.println(filter.mightContain(20));
        // 将元素添加进布隆过滤器
        filter.put(10);
        filter.put(20);
        // 再判断指定元素是否存在
        System.out.println(filter.mightContain(10));
        System.out.println(filter.mightContain(20));
    }

    public static void filterString() {
        // 创建布隆过滤器对象 ，初始化布隆过滤器：预计元素为1000 0000,误差率为千分之3
        BloomFilter<String> filter = BloomFilter.create(
                Funnels.stringFunnel(Charset.defaultCharset()),
                10000000,
                0.001);
        // 判断指定元素是否存在
        System.out.println(filter.mightContain("a"));
        System.out.println(filter.mightContain("b"));
        // 将元素添加进布隆过滤器
        filter.put("a");
        filter.put("b");
        // 再判断指定元素是否存在
        System.out.println(filter.mightContain("a"));
        System.out.println(filter.mightContain("b"));
    }
}
