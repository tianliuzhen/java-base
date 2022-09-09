package com.aaa.javabase.web.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author liuzhen.tian
 * @version 1.0 Person.java  2022/9/9 20:50
 */
public class Person {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private String age;
    @ExcelProperty("性别")
    private String sex;

    public Person(String name,String age,String sex){
        this.name=name;
        this.age=age;
        this.sex=sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
