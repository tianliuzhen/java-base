package com.aaa.javabase.pattern.creater.builder.demo2;

/**
 * description: 定义一个造机器人的标准。一个头、身体、手、脚 造出来的标准
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public interface RootBuilder {
    void buildHead();
    void buildBody();
    void buildHand();
    void buildFoot();

    public Robot createRobot();
}
