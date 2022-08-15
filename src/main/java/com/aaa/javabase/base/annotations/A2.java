package com.aaa.javabase.base.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @author liuzhen.tian
 * @version 1.0 A1.java  2022/8/15 20:26
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={METHOD, TYPE})
public @interface A2 {
}
