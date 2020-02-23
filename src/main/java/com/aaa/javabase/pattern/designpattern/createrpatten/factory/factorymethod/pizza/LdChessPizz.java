package com.aaa.javabase.pattern.designpattern.createrpatten.factory.factorymethod.pizza;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class LdChessPizz extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦奶酪披萨");
        System.out.println("给奶酪披萨准备原材料");
    }
}
