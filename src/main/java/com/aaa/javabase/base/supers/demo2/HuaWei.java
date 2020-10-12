package com.aaa.javabase.base.supers.demo2;

/**
 * @author liuzhen.tian
 * @version 1.0 HuaWei.java  2020/10/12 11:00
 */
public class HuaWei extends Compute {

    private String name="HuaWei";

    void print(){
        System.out.println("this is HuaWei ...");
    }

    void display(){
        print();
        super.print();
        System.out.println(name);
        System.out.println(super.name);
    }

}
