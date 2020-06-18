package com.aaa.javabase.pattern.creater.builder.demo2.reboottype;

import com.aaa.javabase.pattern.creater.builder.demo2.Robot;
import com.aaa.javabase.pattern.creater.builder.demo2.RootBuilder;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public class DanceRobootBuilder implements RootBuilder {
    Robot robot;

    public DanceRobootBuilder() {
        this.robot = new Robot();
    }

    @Override
    public void buildHead() {
        robot.setHead("写入跳舞程序");
    }

    @Override
    public void buildBody() {
        robot.setBody("跳舞机器人-身体");
    }

    @Override
    public void buildHand() {
        robot.setHand("跳舞机器人-手");
    }

    @Override
    public void buildFoot() {
        robot.setFoot("跳舞机器人-脚");
    }

    @Override
    public Robot createRobot() {
        return robot;
    }

}
