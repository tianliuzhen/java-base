package com.aaa.javabase.base.获取泛型参数;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型信息只有在子类的帮助下才能被取出，如果直接取  不可避免地会被Java编译器进行类型擦除
 * 一种良好的编程实践是将带有泛型的类声明为abstract。
 * <p>
 * 如果是继承基类而来的泛型，就用 getGenericSuperclass() , 转型为 ParameterizedType 来获得实际类型
 *
 * @author liuzhen.tian
 * @version 1.0 TestClass.java  2022/1/13 21:59
 */
public class TestClass {

    /**
     * 泛型父类 GenericA
     *
     * @param <T>
     * @param <M>
     */
    public class GenericA<T, M> {

    }

    /**
     * 泛型父类 GenericA 子类
     */
    class GenericB extends GenericA<String, Integer> {

        // 方法泛型擦参数
        public List<String> toStringList() {
            return null;
        }

        // 方法泛型擦参数
        public List<String> toStringList2(List<Integer> list) {
            return null;
        }


    }

    public static void main(String[] args) throws NoSuchMethodException {

        // 获取类 - 泛型参数
        ParameterizedType parameterizedType = (ParameterizedType) GenericB.class.getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument);
        }
        System.out.println("============================");

        // 获取方法 - 返回值泛型参数
        Method method = GenericB.class.getDeclaredMethod("toStringList");
        // 方法返回值 泛型类型
        Type typeClass = method.getGenericReturnType();
        System.out.println(typeClass);
        if (typeClass instanceof ParameterizedType) {
            Type actualType2 = ((ParameterizedType) typeClass).getActualTypeArguments()[0];
            System.out.println(actualType2);
        } else {
            System.out.println(typeClass + " is Not ParameterizedType");
        }


        System.out.println("============================");

        // 获取方法参数 - 泛型参数
        Method method2 = GenericB.class.getDeclaredMethod("toStringList2", List.class);
        Type[] genericParameterTypes = method2.getGenericParameterTypes();
        Arrays.stream(genericParameterTypes).forEach(System.out::println);
    }
}
