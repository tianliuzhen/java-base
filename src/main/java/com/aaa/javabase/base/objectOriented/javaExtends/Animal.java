package com.aaa.javabase.base.objectOriented.javaExtends;

/**
 * description: 动物的通用类
 *  从 mouse（老鼠类）、penguin（企鹅类）  这两段代码可以看出来，
 *  代码存在重复了，导致后果就是代码量大且臃肿，而且维护性不高(维护性主要是后期需要修改的时候，
 *  就需要修改很多的代码，容易出错)，所以要从根本上解决这两段代码的问题，就需要继承，
 *  将两段代码中相同的部分提取出来组成 一个父类
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */

public class Animal {
    private String name;
    private int id;
    public Animal(String myName, int myid) {
        name = myName;
        id = myid;
    }
    public void eat(){
        System.out.println(name+"正在吃");
    }
    public void sleep(){
        System.out.println(name+"正在睡");
    }
    public void introduction() {
        System.out.println("大家好！我是"         + id + "号" + name + ".");
    }
}

