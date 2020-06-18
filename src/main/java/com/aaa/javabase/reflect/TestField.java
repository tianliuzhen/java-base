package com.aaa.javabase.reflect;

import com.aaa.javabase.reflect.domain.Person;

import java.lang.reflect.Field;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/18
 */
public class TestField {
    public static void main(String[] args) throws Exception {
        // 成员变量
//        TestField();
        TestField2();
    }
    public static void TestField2() throws Exception {

        Class clazz = Class.forName("com.aaa.javabase.reflect.domain.Person");
        Object obj = clazz.newInstance();//通过反射创建对象

        // 调用getDeclaredField("name") 取得name属性对应的Field对象
                Field f = clazz.getDeclaredField("name");

        // 取消属性的访问权限控制，即使private属性也可以进行访问。
                f.setAccessible(true);
        // 调用get()方法取得对应属性值。
                System.out.println(f.get(obj));  //相当于obj.getName();
        // 调用set()方法给对应属性赋值。
                f.set(obj, "lkl");  //相当于obj.setName("lkl");
        // 调用get()方法取得对应属性修改后的值。
                System.out.println(f.get(obj));
    }




    public static void TestField() throws Exception {
        //0、获取Person的class 对象
        Class<Person> personClass = Person.class;
        /**
         1、获取成员变量们
         Field[] getFields()
         Field getField(String name)]

         Field[] getDeclaredFields()
         FieldgetDeclaredField(String name)
         */
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field a = personClass.getField("a");
        System.out.println(a);
        System.out.println("-----------------");
        //获取成员变量 a 的值
        Person p = new Person();
        Object o = a.get(p);
        a.set(p,"tom");
        System.out.println(o);
        System.out.println(p);
        System.out.println("------DeclaredFields-----------");
        // getDeclaredFields() 获取所有的成员变量，不考虑修饰符
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        Field d = personClass.getDeclaredField("d");
        // 忽略访问权限修饰符的安全检查
        d.setAccessible(true);
        Object o1 = d.get(p);
        System.out.println(o1);
        d.set(p,"tom-d");
        System.out.println(p);
    }
}
