package com.aaa.javabase.spring.injection.setter;

/**
 * @author liuzhen.tian
 * @version 1.0 A1bean.java  2023/9/27 21:40
 */
public class A1bean {

    public A2bean getaA5bean() {
        return a2bean;
    }

    public void setA2bean(A2bean a2bean) {
        this.a2bean = a2bean;
    }

    private A2bean a2bean;

    public String say() {
        return "saaa";
    }
}
