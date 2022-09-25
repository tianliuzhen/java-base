package com.aaa.javabase.base.objectOriented.polymorphism;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */
public abstract class Employee {
    private String name;
    private String address;
    private int number;

    public Employee() {
    }

    public Employee(String name, String address, int number) {
        System.out.println("Employee 构造函数");
        this.name = name;
        this.address = address;
        this.number = number;
    }
    public abstract double computePay();

    public void mailCheck() {
        System.out.println("邮寄支票给： " + this.name
                + " " + this.address);
    }
    @Override
    public String toString() {
        return name + " " + address + " " + number;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String newAddress) {
        address = newAddress;
    }
    public int getNumber() {
        return number;
    }
}
