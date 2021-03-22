package com.aaa.javabase.jdk8.函数接口.function;

import java.util.function.Function;

/**
 * Function 函数转换
 * @author liuzhen.tian
 * @version 1.0 TestConvertDto.java  2020/8/8 16:56
 */
public class TestFunction {

    public static void main(String[] args) {
        Dto1 dto1 = new Dto1();
        Dto2 apply = dto1Dto2Function.apply(dto1);
        System.out.println(apply);

    }

    /**
     * 写法1
     * Dto1 入参
     * Dto2 出参
     */
    public static Function<Dto1, Dto2> dto1Dto2Function = (Dto1 dto1) -> {
        Dto2 dto2 = new Dto2();
        dto2.setName2(dto1.getName1());
        return dto2;
    };

    /**
     * 写法2
     * Dto1 入参
     * Dto2 出参
     */
    public static Function<Dto1, Dto2> dto2Dto2Function = dto1 -> {
        Dto2 dto2 = new Dto2();
        dto2.setName2(dto1.getName1());
        return dto2;
    };

}
