package com.aaa.javabase.base.序列化;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzhen.tian
 * @version 1.0 SvBean.java  2023/9/9 19:30
 */

@Data
public class SvBean implements Serializable {
    private static final long serialVersionUID = -4197147695838111201L;

    private int age;
    private String name;

    // private int score;

    public SvBean(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
