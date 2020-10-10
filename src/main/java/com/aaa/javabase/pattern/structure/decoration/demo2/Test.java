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


        /**
         * 这里递归的算法有点绕...  这里 debug 已经绕晕了
         *   new Milk(new Chocolate(new Decaf()));
         *        1       2          3
         *   解析：执行顺序 1=》2=》3 ，先调用 new Milk 再调用 new Chocolate 最后调用 new Decaf
         *        这里的问题是 从第一步开始就已经进入递归，再加上cost() 是个多态方法...
         *
         * 譬如这里  order3.cost() 调用的是 Decorator.cost()
         *         1. 是调用的  new Milk() 的  float cost = drink.cost();
         *                                  此时根据 多态的运行机制会再次执行 Decorator.cost()
         *         2.  接着递归到  调用 new Chocolate() 的 new Decaf() 的 cost方法 （Coffee.cost()）
         *
         *         3.  执行完毕之后 再次返回来 继续走 步骤 1. 后面的  float cost = drink.cost();  后面的步骤
         *
         */
        // case 3: 浓缩*1 巧克力*1  牛奶*1
        Milk order3 = new Milk(new Chocolate(new Decaf()));
        System.out.println("order3.cost() = " + order3.cost());
        System.out.println("order3.getDescription() = " + order3.getDescription());

        // case 4: ...
    }
}
