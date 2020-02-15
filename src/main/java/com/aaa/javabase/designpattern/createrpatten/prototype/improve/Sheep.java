package com.aaa.javabase.designpattern.createrpatten.prototype.improve;

/**
 * description: 原型模式创建的对象是一个（模板）对象
 *  spring 创建 bean 用到 单例和多例
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/14
 */
public class Sheep implements Cloneable {
    private String name;
    private int age;
    private String color;
    public Sheep friend;

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }

    /**
     * 克隆该实例，使用默认的clone() 方法完成
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Sheep sheep=null;
        sheep= (Sheep) super.clone();
        return sheep;
    }
}
