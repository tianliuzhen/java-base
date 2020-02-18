package com.aaa.javabase.reflect;

import com.aaa.javabase.reflect.domain.Person;
import com.aaa.javabase.reflect.domain.Stuent;

/**
 * description: 描述
 * 1、class.forName("全类名")     ：将字节码文件加载进内存，返回class对象
 * 2、类名.class ：通过类名的属性class 获取
 * 3、对象.getClass ：getclass（）方法在object类定义者
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public class ReflectDemo1 {
    public static void main(String[] args) throws Exception {
        //1、class.forName("全类名")
        Class aClass = Class.forName("com.aaa.javabase.reflect.domain.Person");
        System.out.println(aClass);
        // 2、类名.class ：通过类名的属性class 获取
        Class personClass = Person.class;
        System.out.println(personClass);
        //3、对象.getClass ：getclass（）方法在object类定义者
        Person person = new Person();
        Class aClass1 = person.getClass();
        System.out.println(aClass1);
        // == 比较三个对象
        System.out.println( aClass == personClass );
        System.out.println( aClass1 == personClass );

        Class stuentClass = Stuent.class;

        System.out.println(stuentClass == aClass1 );
    }
}
