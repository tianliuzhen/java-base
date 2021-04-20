package com.aaa.javabase.pattern.behavior.strategy.annotion;

import com.aaa.javabase.pattern.behavior.strategy.constant.InspectionEnum;

import java.lang.annotation.*;

/**
 * @author liuzhen.tian
 * @version 1.0 ChooserName.java  2021/4/20 23:12
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChooserName {
    InspectionEnum value();
}
