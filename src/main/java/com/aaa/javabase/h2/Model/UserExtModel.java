package com.aaa.javabase.h2.Model;

import lombok.Data;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 UserExtModel.java  2023/2/16 20:52
 */

@Data
public class UserExtModel {
    private Long id;

    private String name;

    private Integer age;

    private String email;

    private String userId;

    private String deptNo;

    /**
     * 部门
     */
    private Dept dept;

    /**
     * 部门
     */
    private List<Dept> deptList;
}
