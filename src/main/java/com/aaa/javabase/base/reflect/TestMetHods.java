package com.aaa.javabase.base.reflect;

import com.aaa.javabase.base.reflect.domain.Person;

import java.lang.reflect.Method;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/18
 */
public class TestMetHods {
    public static void main(String[] args) throws Exception {
        // 测试构造方法
        TestMethods();
    }

    public static void TestMethods() throws Exception {
        //0、获取Person的class 对象
        Class<Person> personClass = Person.class;
        /**
         Methods[] getMethods()
         Method getMethods(String name,类<?>... parameterTypes)

         Methods[] getDeclaredMethods()
         Method getDeclaredMethods(String name,类<?>... parameterTypes)
         */
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
            System.out.println(method.getName());
        }
        //获取指定名称的方法
        Method m1 = personClass.getMethod("m1");
        Person person = new Person();
        Object invoke = m1.invoke(person);
        System.out.println("----带返回值的方法----");
        Method m1_return = personClass.getMethod("m1_return",String.class);
        Object allen = m1_return.invoke(person, "allen");
        System.out.println(allen);
        System.out.println("-----------测试获取 private 修饰的方法-----------");
        Method declaredMethod = personClass.getDeclaredMethod("m1_return2",String.class);
        declaredMethod.setAccessible(true);
        Object allen2 = declaredMethod.invoke(person, "allen2");
        System.out.println(allen2);
        System.out.println("------------------测试类名-----------------------");
        System.out.println(personClass.getName());
    }
}
