package com.aaa.javabase.spring.injection.objectProvider;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * @author liuzhen.tian
 * @version 1.0 MyQualifier.java  2024/12/5 23:07
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface MyQualifier {

    String value() default "";

}
