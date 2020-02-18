package com.aaa.javabase.reflect;

import com.aaa.javabase.reflect.domain.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/18
 */
public class TestConstructor {
    public static void main(String[] args) throws Exception {
        // 测试构造方法
        TestConstructor();
    }

    public static void TestConstructor() throws Exception {
        //0、获取Person的class 对象
        Class<Person> personClass = Person.class;
        /**
         Constructor<?>[] getConstructors()
         Constructor<T>[] getConstructor(类<?>... parmeterTypes)

         Constructor<?>[] getDeclaredConstructors()
         Constructor<T>[] getDeclaredConstructor(类<?>... parmeterTypes)
         */
        //获取所有的构造方法
        Constructor<?>[] constructors = personClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("------");
        // 获取单个 getConstructor  可设置值 进行取无参数还是有参数
        Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
        System.out.println(constructor);
        //创建对象
        Person allen = constructor.newInstance("allen", 12);
        System.out.println(allen);
        System.out.println("--------测试构造空参数----------");
        Constructor<Person> constructor1 = personClass.getConstructor();
        Person person = constructor1.newInstance();
        System.out.println(person);
        System.out.println("--------测试构造getDeclaredConstructor----------");
        Person person1 = personClass.newInstance();
        System.out.println(person1);
        System.out.println("--------测试构造空参数--简化----------");
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class);
        // 测试暴力反射
        declaredConstructor.setAccessible(true);
        Person bob = declaredConstructor.newInstance("bob");
        System.out.println(bob);
    }
}
