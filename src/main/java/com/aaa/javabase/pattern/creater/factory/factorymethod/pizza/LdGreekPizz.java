package com.aaa.javabase.pattern.creater.factory.factorymethod.pizza;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class LdGreekPizz extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦希腊披萨");
        System.out.println("给希腊披萨准备原材料");
    }
}
