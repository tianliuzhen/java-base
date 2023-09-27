package com.aaa.javabase.spring.aop;

import java.lang.annotation.*;

/**
 * @author liuzhen.tian
 * @version 1.0 SysLog1.java  2023/9/17 14:47
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
