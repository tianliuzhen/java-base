package com.aaa.javabase.base.引用传递误区;

import lombok.ToString;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/6/5 10:56
 */
public class TestMain {
    public static void main(String[] args) {
        Person p1 = new Person("allen");
        // 修改p1引用的对象
        updatePerson(p1);
        System.out.println(p1);
    }

    /**
     * 形参 p是一个新的变量，假设引用地址为 001，这里类似于执行了p=p1，
     * 形参 p 和 实参 p1，共用一个引用地址 001。
     *
     * @param p p1
     */
    public static void updatePerson(Person p) {
        /**
         * 这里 new Person("mike")，表示修改形参p的引用地址为 002，实参 p1的引用地址仍然为 001
         * 所以这里的 =赋值 ，对外面的p1没有任何影响
         */
        p = new Person("mike");
        // 同理，也对外面的p1也没有影响
        p = null;
    }
}

@ToString
class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}
