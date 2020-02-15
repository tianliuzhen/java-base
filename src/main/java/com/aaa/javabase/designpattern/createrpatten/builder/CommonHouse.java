package com.aaa.javabase.designpattern.createrpatten.builder;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public class CommonHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println("给普通房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("给普通方式砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("给普通方式封顶");
    }
}
