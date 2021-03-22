package com.aaa.javabase.jdk8.stream.maps;

import com.aaa.javabase.domain.Grade;
import com.aaa.javabase.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author liuzhen.tian
 * @version 1.0 TestGradeAndStudent.java  2020/11/2 18:16
 */
public class TestGradeAndStudent {


    public static void main(String[] args) {
        TestGradeAndStudent testGradeAndStudent = new TestGradeAndStudent();
        testGradeAndStudent.test();
    }
    public void test(){
    // 统计所有班级里所有同学的年龄分布
    // 方法一，(不推荐)不够直观
        List<Grade> gradeList = Grade.getGrade();
        List<List<Student>> collect = gradeList.stream().map(Grade::getStudent).collect(Collectors.toList());
     // 方法一，推荐
        List<Integer> collect1 = gradeList.stream().flatMap(grade -> grade.getStudent().stream())
                .map(Student::getAge).collect(Collectors.toList());

        collect1.forEach(System.out::println);

    }


}
