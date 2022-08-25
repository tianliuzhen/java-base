package com.aaa.javabase.domain;

import lombok.Data;

import java.util.Currency;

/**
 * @author liuzhen.tian
 * @version 1.0 MyMoney.java  2022/8/25 20:44
 */

@Data
public class MyMoney {
    private Long cent;
    private Currency currency;

    public static final String sss="11";

    // 在已实现序列化的类中,被transient修饰的变量不参与序列化和反序列化
    transient  final int numericCode=1;
}
