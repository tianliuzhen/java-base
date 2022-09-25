package com.aaa.javabase.base.reflect.domain;

import java.util.Objects;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public class Person {
    private String name;
    private int age;
    // 四种权限修饰符不同的变量
    public String a;
    protected String b;
     String c;
    private String d;

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                '}';
    }
    // 四种权限修饰符不同的方法
    public void m1(){
        System.out.println("m1...方法");
    }
    public String  m1_return(String name){
        System.out.println("m1...方法");
        return name;
    }

    private String  m1_return2(String name){
        System.out.println("m1...方法");
        return name;
    }

    private void m2(){
        System.out.println("m2...方法");
    }
    protected void m3(){
        System.out.println("m3...方法");
    }
    void m4(){
        System.out.println("m4...方法");
    }

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     Person person = (Person) o;
    //     return age == person.age &&
    //             Objects.equals(name, person.name) &&
    //             Objects.equals(a, person.a) &&
    //             Objects.equals(b, person.b) &&
    //             Objects.equals(c, person.c) &&
    //             Objects.equals(d, person.d);
    // }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, a, b, c, d);
    }
}
