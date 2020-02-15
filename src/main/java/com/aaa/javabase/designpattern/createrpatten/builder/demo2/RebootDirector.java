package com.aaa.javabase.designpattern.createrpatten.builder.demo2;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public class RebootDirector {
    public Robot  HumanCreateRobotByDirector(RootBuilder ibr){
        ibr.buildBody();
        ibr.buildFoot();
        ibr.buildHand();
        ibr.buildHead();
        return ibr.createRobot();
    }
}
