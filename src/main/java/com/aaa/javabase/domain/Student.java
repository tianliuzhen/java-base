package com.aaa.javabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuzhen.tian
 * @version 1.0 Dto.java  2020/10/9 17:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Cloneable, Serializable, Comparable<Student> {
    private String name="";
    private Integer age=0;

    public Student(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Student o) {
        //默认是升序
        //降序的话，反过来操作就好了  o.getAge().compareTo(this.age.getAge());
        return this.age.compareTo(o.getAge());
    }
}
