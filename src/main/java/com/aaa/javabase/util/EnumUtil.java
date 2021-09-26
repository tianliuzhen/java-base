package com.aaa.javabase.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author liuzhen.tian
 * @version 1.0 EnumUtil.java  2021/9/26 20:27
 */
public class EnumUtil {

    /**
     * 根据code 获取枚举值 (写法1：反射直接获取方法返回值)
     *
     * @param cla  class
     * @param code code
     * @param <T>  枚举泛型
     * @return T
     */
    public static <T> T getEnumByCode(Class<T> cla, Object code) {
        // 获取 枚举 values
        T[] enumConstants = cla.getEnumConstants();
        try {
            // 需要统一枚举值属性为 code
            Method method = cla.getMethod("getCode");
            for (T enumConstant : enumConstants) {
                Object value = method.invoke(enumConstant);
                if (value.equals(code)) {
                    return enumConstant;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据code 获取枚举值 (写法1：反射直接获取方法返回值)
     *
     * @param cla  class
     * @param code code
     * @param <T>  枚举泛型
     * @return T
     */
    public static <T> String getStringByCode(Class<T> cla, Object code) {
        // 获取 枚举 values
        T[] enumConstants = cla.getEnumConstants();
        try {
            // 需要统一枚举值属性为 code
            Method methodCode = cla.getMethod("getCode");
            Method methodDesc = cla.getMethod("getDesc");
            for (T enumConstant : enumConstants) {
                Object value = methodCode.invoke(enumConstant);
                if (value.equals(code)) {
                    return methodDesc.invoke(enumConstant).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据code 获取枚举值 (写法1：反射直接获取属性值)
     *
     * @param cla  class
     * @param code code
     * @param <T>  枚举泛型
     * @return T
     */
    public static <T> T getEnumByCodeV2(Class<T> cla, Object code) {
        T[] enumConstants = cla.getEnumConstants();
        try {
            // 需要统一枚举值属性为 code
            Field field = cla.getDeclaredField("code");
            field.setAccessible(true);
            for (T enumConstant : enumConstants) {
                Object value = field.get(enumConstant);
                if (value.equals(code)) {
                    return enumConstant;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据code 获取枚举值 (写法2：反射直接获取属性值)
     *
     * @param cla  class
     * @param code code
     * @param <T>  枚举泛型
     * @return T
     */
    public static <T> String getStringByCodeV2(Class<T> cla, Object code) {
        // 获取 枚举 values
        T[] enumConstants = cla.getEnumConstants();
        try {
            // 需要统一枚举值属性为 code
            Field fieldCode = cla.getDeclaredField("code");
            fieldCode.setAccessible(true);

            // 需要统一枚举值属性为 desc
            Field fieldDesc = cla.getDeclaredField("desc");
            fieldDesc.setAccessible(true);

            for (T enumConstant : enumConstants) {
                Object value = fieldCode.get(enumConstant);
                if (value.equals(code)) {
                    return fieldDesc.get(enumConstant).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
