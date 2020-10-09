package com.aaa.javabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 A.java  2020/10/9 17:39
 */
@Data
@AllArgsConstructor
public class Teacher implements Cloneable {
    public String name;
    public List<Student> list;
    public Student dto;



    @Override
    public Object clone() {
        Teacher o = null;
        try {
            o = (Teacher) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
