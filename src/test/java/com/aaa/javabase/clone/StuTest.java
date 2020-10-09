package com.aaa.javabase.clone;

import com.aaa.javabase.domain.Student;
import com.aaa.javabase.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * list 深复制,
 * @author liuzhen.tian
 * @version 1.0 StuTest.java  2020/10/9 17:49
 */
public class StuTest {

    public static void main(String[] args) {

        List<Teacher> copy=new ArrayList<Teacher>();
        List<Teacher> src=new ArrayList< Teacher>();

        Student d=new Student("dto_a");
        Teacher a=new Teacher("aa",getDto(),d);
        src.add(a);

        for (int i = 0; i < src.size(); i++) {
            // list 深复制, 遍历每个个对象的 clone ，再次保存
            copy.add((Teacher)src.get(i).clone());
        }
        copy.get(0).setName("zz1");
        copy.get(0).setDto(new Student("zz2"));
        copy.get(0).setList(changeDto());
        System.out.println("copy = " + copy);
        System.out.println("src = " + src);
    }
    public static List<Student> getDto(){
        List<Student> list=new ArrayList<>();
        Student d=new Student();
        d.setAge(15);
        d.setName("tom");
        list.add(d);
        return  list;
    }
    public static List<Student> changeDto(){
        List<Student> list=new ArrayList<>();
        Student d=new Student();
        d.setAge(16);
        d.setName("zz3");
        list.add(d);
        return  list;
    }
}
