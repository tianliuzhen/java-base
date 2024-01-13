package com.aaa.javabase.spring.Listener;

import com.aaa.javabase.spring.Listener.非注解.OrderEvent2;

/**
 * @author liuzhen.tian
 * @version 1.0 Main.java  2024/1/13 18:16
 */
public class Main {
    public static void main(String[] args) {
        OrderEvent2 orderEvent2 = new OrderEvent2(null, "");
        System.out.println(orderEvent2.getMsg());


    }
}
