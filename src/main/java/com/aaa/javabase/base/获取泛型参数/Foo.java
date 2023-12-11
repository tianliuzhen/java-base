package com.aaa.javabase.base.获取泛型参数;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 参数化类型可以是嵌套的泛型类或接口的参数化。例如，如果non-generic类C具有泛型成员类D<T>，则C.D<Object>是参数化类型。如果泛型类C<T>具有non-generic成员类D，则成员类型C<String>.D是参数化类型，即使类D不是泛型。
 * <p>
 * 所以C<String>.D也是一个ParameterizedType！这就是getActualTypeArguments的文件所指的。根据文档，getActualTypeArguments将为C<String>.D返回一个空数组。以下是一个例子：
 *
 * @author liuzhen.tian
 * @version 1.0 Foo.java  2022/10/31 22:18
 */
public class Foo<T> {
    class Bar {

    }

    public static class TestMain {
        public Foo<Integer>.Bar bar;

        public static void main(String[] args) throws Exception {
            ParameterizedType parameterizedType = (ParameterizedType) TestMain.class.getDeclaredField("bar").getGenericType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Type rawType = parameterizedType.getRawType();
            Type ownerType = parameterizedType.getOwnerType();
            System.out.println();
        }
    }
}
