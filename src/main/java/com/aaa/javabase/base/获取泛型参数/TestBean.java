package com.aaa.javabase.base.获取泛型参数;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 TestBean.java  2022/10/31 21:31
 */
public class TestBean<E> {
    private List list4;
    private String name;
    private E e;
    private List<E> list;
    private List<?> list2;
    private List<String> list3;
    private Map<String, Long> map;
    private Map.Entry<String, Long> map2;

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.aaa.javabase.base.获取泛型参数.TestBean");
        // 反射获取所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("");
            System.out.println("name ==" + field.getName());
            Type type = field.getGenericType();
            System.out.println("getGenericType ==" + type);
            boolean isParameterizedType = type instanceof ParameterizedType;
            System.out.println("是否是参数化类型 ==" + isParameterizedType);
            if (isParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                Type rawType = parameterizedType.getRawType();
                Type ownerType = parameterizedType.getOwnerType();
                System.out.println();
            }
        }
    }
}
