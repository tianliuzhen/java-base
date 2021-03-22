package com.aaa.javabase.jdk8.optional;

import java.util.Optional;

/**
 * @author liuzhen.tian
 * @version 1.0 TestError.java  2021/3/22 22:31
 */
public class TestErrorCase1 {

    /**
     * 测试 Optional，赋值默认值写法
     * @param args
     */
    public static void main(String[] args) {
        // 正确写法1
        Object str1 = Optional.ofNullable(null).orElse("默认值1");
        System.out.println(str1);

        // 正确写法2
        Optional<Object> str2 = Optional.ofNullable(null);
        Object strNew2 = str2.orElse("默认值2");
        System.out.println(strNew2);

        // 错误写法
        Optional<Object> str3 = Optional.ofNullable(null);
        str2.orElse("默认值3");
        System.out.println(str2);
    }
}
