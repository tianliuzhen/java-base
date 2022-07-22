package com.aaa.javabase.function.annotion;

import java.lang.annotation.*;

/**
 * 自定义公共函数注解
 *
 * @author liuzhen.tian
 * @version 1.0 KeywordDefinition.java  2022/7/21 20:47
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FunctionDefinition {
    /**
     * 函数目录
     */
    String functionModule();

    /**
     * 函数名字
     */
    String functionName();
}
