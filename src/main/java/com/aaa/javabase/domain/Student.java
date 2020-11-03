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
public class Student implements Cloneable, Serializable {
    private String name="";
    private Integer age=0;

    public Student(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
