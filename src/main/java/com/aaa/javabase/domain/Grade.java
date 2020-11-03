package com.aaa.javabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 Grade.java  2020/11/2 18:14
 */
@Data
@AllArgsConstructor
public class Grade {
    private Long classId;
    private String className;
    private List<Student> student;
}
