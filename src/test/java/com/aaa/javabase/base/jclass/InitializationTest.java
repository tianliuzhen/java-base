package com.aaa.javabase.base.jclass;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author liuzhen.tian
 * @version 1.0 InitializationTest.java  2024/4/9 21:42
 */
public class InitializationTest {
    /**
     * 不会生成<clinit>场景
     */
    // 场景1：对于非静态的字段，不管是否进行了显式赋值，都不会生成<cLinit>方法
    public int num = 1; // 准备阶段赋值
    // 场景2：静态的字段，没有显式的赋值，不会生成<clinit>方法
    public static int num1; // 准备阶段赋值
    // 场景3：对于声明为static final的基本数据类型的字段，不管是否进行了显式赋值，都不会生成<clinit>方法
    public static final int num2 = 2; // 准备阶段赋值
    public static final String str = "hello 1"; // 准备阶段赋值

    /**
     * 会生成<clinit>场景
     */
    // 场景1：对于声明为static final的非基本数据类型的字段，会生成<clinit>方法
    // public static final Integer num3 = 3; // 初始化阶段赋值
    // public static final Integer num4 = Integer.valueOf(4); // 初始化阶段赋值
    // public static final String str2 = new String("hello 2"); // 初始化阶段赋值
    public int num5 = new Random().nextInt();
    public Map map = new HashMap(){{
        System.out.println(1);
    }};
}
