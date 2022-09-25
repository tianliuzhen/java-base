package com.aaa.javabase.base.objectOriented.polymorphism;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */

public class VirtualDemo {
    public static void main(String [] args) {
        Salary s = new Salary("员工 A", "北京", 3, 3600.00);
        Employee e = new Salary("员工 B", "上海", 2, 2400.00);
        System.out.println("使用 Salary 的引用调用 mailCheck -- ");
        s.mailCheck();
        System.out.println("\n使用 Employee 的引用调用 mailCheck--");
        e.mailCheck();
        System.out.println("------------------");
        Tom t = new Tom("员工 c", "厦门", 2);
        t.mailCheck();
        Employee e2=new Tom("员工 d", "杭州", 3);
        e2.mailCheck();


    }
}

