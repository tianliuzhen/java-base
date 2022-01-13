package com.aaa.javabase.base.获取泛型参数;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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

    }

    public static void main(String[] args) {
        ParameterizedType parameterizedType = (ParameterizedType) GenericB.class.getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument);
        }
    }
}
