package com.aaa.javabase.domain;

/**
 * 枚举通用基类
 *
 * @author liuzhen.tian
 * @version 1.0 BaseNum.java  2022/2/19 19:56
 */
public interface BaseNum {

    /**
     * 规范定义枚举类，方便通过泛型工具类读取
     * 获取 枚举值code
     */
    String getCode();
}
