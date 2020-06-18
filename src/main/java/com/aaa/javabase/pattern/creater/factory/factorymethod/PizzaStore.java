package com.aaa.javabase.pattern.creater.factory.factorymethod;

import com.aaa.javabase.pattern.creater.factory.factorymethod.order.BiOrderPizza;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/11
 */
public class PizzaStore {
    public static void main(String[] args) {
        // 创建北京口味披萨
        new BiOrderPizza();
    }
}
