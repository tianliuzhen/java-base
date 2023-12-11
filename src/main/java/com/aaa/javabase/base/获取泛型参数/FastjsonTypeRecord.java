package com.aaa.javabase.base.获取泛型参数;

import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 FastjsonTypeRecord.java  2023/12/11 21:39
 */
public class FastjsonTypeRecord {
    private Map<String, String> myGenericClass;
    private List<String> list;
    private List<Map<String, String>> listMap;


    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.aaa.javabase.base.获取泛型参数.FastjsonTypeRecord");
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
                // 获取真实的泛型参数
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                // 获取泛型参数最外边<>属性
                Type rawType = parameterizedType.getRawType();
                // 返回静态内部类的外部类
                Type ownerType = parameterizedType.getOwnerType();
                System.out.println();

                Type actualTypeArgument = actualTypeArguments[0];
                if ("java.util.List".equals(rawType.getTypeName())) {
                    // 如果最外边的属性是list那么 actualTypeArguments.length恒等于1
                    if (actualTypeArguments.length == 1) {
                        if (isWrapperType(actualTypeArgument.getTypeName())) {
                            String text = "[\"aa\",\"bb\"]";
                            String text2 = "[1,2]";
                            List<Object> strings = JSONArray.parseArray(text2, Object.class);
                        } else {
                            String text = "[{\"id\":1001,\"name\":\"Jobs\"}]";
                            /**
                             * fixme fastjson有bug。此方法的第一个入参，
                             * 不能是简单类型："[\"aa\",\"bb\"]";
                             * 只能是复杂类型："[{\"id\":1001,\"name\":\"Jobs\"}]";
                             * <p/>
                             * com.alibaba.fastjson.JSON#parseArray(java.lang.String, java.lang.reflect.Type[])
                             */
                            List<Object> objects = JSONArray.parseArray(text, actualTypeArguments);
                        }
                    }
                }


            }
        }
    }

    /**
     * 判断是不是String和基本数据类型包装类
     *
     * @param className
     * @return
     */
    public static boolean isWrapperType(String className) {
        try {
            Class<?> cls = Class.forName(className);
            return cls == String.class || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class
                    || cls == Float.class || cls == Double.class || cls == Character.class || cls == Boolean.class;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
