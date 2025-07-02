package com.aaa.javabase.util;


import com.aaa.javabase.common.MyFun;
import com.aaa.javabase.domain.Student;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author liuzhen.tian
 * @version 1.0 FunUtil.java  2023/7/30 18:10
 */
public class ProperUtil {
    private static final Pattern GET_PATTERN = Pattern.compile("^get[A-Z].*");
    private static final Pattern IS_PATTERN = Pattern.compile("^is[A-Z].*");

    private ProperUtil() {
    }

    public static <T, R> String getFieldName(MyFun<T, R> fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(fn);
            String getter = serializedLambda.getImplMethodName();
            if (GET_PATTERN.matcher(getter).matches()) {
                getter = getter.substring(3);
            } else if (IS_PATTERN.matcher(getter).matches()) {
                getter = getter.substring(2);
            }
            return Introspector.decapitalize(getter);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("解析serializedLambda失败:" + e);
        }
    }

    public static void main(String[] args) {
        String fieldName = ProperUtil.<Student,String>getFieldName(Student::getName);
        System.out.println(fieldName);
    }
}
