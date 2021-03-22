package com.aaa.javabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 Grade.java  2020/11/2 18:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private Long classId;
    private String className;
    private List<Student> student;

    public static List<Grade> getGrade(){
        List<Grade> grades = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            grades.add(new Grade((long) i, i + "班", getGradeStudent(i)));
        }
        return grades;
    }
    public static List<Student> getGradeStudent(int gradeId){
        List<Student> students = new ArrayList<>();
        for (int i = (gradeId-1) * 10; i < (10 * gradeId); i++) {
            students.add(new Student("同学" + i, i + 20));
        }
        return students;
    }
}
