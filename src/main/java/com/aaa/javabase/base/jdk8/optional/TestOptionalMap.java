package com.aaa.javabase.base.jdk8.optional;

import com.aaa.javabase.base.jdk8.函数接口.function.Dto1;

import java.util.Optional;

/**
 * @author liuzhen.tian
 * @version 1.0 TestOptional1.java  2021/3/22 22:35
 */
public class TestOptionalMap {
    public static void main(String[] args) {

        // case1：对象不为null，属性为null
        Dto1 dto1 = new Dto1();
        dto1.setName1(null);// 这里是null 才有效果，"" 无效。
        String str = Optional.ofNullable(dto1).map(Dto1::getName1).orElse("默认值");
        System.out.println(str);

        // case2：对象为null，属性为null
        Dto1 dto2 = null;
        String str2 = Optional.ofNullable(dto1).map(Dto1::getName1).orElse("默认值");
        System.out.println(str2);

    }
}
