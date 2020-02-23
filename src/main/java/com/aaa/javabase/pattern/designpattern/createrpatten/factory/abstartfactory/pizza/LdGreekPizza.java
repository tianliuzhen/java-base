package com.aaa.javabase.pattern.designpattern.createrpatten.factory.abstartfactory.pizza;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class LdGreekPizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦希腊披萨");
        System.out.println("给希腊披萨准备原材料");
    }
}
