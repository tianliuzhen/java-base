package com.aaa.javabase.domain.dto;

import java.util.HashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 WxUserDTO.java  2024/5/9 21:35
 */
public class WxUserDTO {
    private String name;
    private int age;

    private HashMap address = new HashMap() {{
        System.out.println("普通属性初始化");
    }};

    private static HashMap addressStatic = new HashMap() {{
        System.out.println("静态属性初始化");
    }};

    {
        System.out.println("普通代码块初始化");
    }

    static {
        System.out.println("静态代码块初始化");
    }

    public WxUserDTO(String name, int age) {
        System.out.println("WxUserDTO有参构造初始化");
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public WxUserDTO() {
    }
}
