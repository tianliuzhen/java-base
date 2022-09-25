package com.aaa.javabase.base.objectOriented.javaExtends.oldAnimals;

/**
 * description: 企鹅类
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */
public class Penguin {
    private String name;
    private int id;
    public Penguin(String myName, int  myid) {
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
