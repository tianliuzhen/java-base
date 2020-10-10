package com.aaa.javabase.pattern.structure.decoration.demo2;


import com.aaa.javabase.pattern.structure.decoration.demo2.type.Decaf;
import com.aaa.javabase.pattern.structure.decoration.demo2.type.ShortBlack;
import com.aaa.javabase.pattern.structure.decoration.demo2.type2.Chocolate;
import com.aaa.javabase.pattern.structure.decoration.demo2.type2.Milk;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2020/10/10 14:49
 */
public class Test {
    public static void main(String[] args) {

        // 一把咖啡都是按调料收费的，所以可能出现两份调料的订单

        // case 1： 浓缩*1 巧克力*1
        // Chocolate order = new Chocolate(new ShortBlack());
        // System.out.println("order = " + order.cost());
        // System.out.println("order = " + order.getDescription());


        // case 2： 浓缩*1 巧克力*2
        // Chocolate order2 = new Chocolate(new Chocolate(new ShortBlack()));
        // System.out.println("order2.getDescription() = " + order2.getDescription());
        // System.out.println("order2.cost() = " + order2.cost());

        // case 3: 浓缩*1 巧克力*1  牛奶*1
        Milk order3 = new  Milk(new Chocolate(new Decaf()));
        System.out.println("order3.cost() = " + order3.cost());
        System.out.println("order3.getDescription() = " + order3.getDescription());
    }
}
