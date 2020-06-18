package com.aaa.javabase.pattern.creater.builder.demo2;

import com.aaa.javabase.pattern.creater.builder.demo2.reboottype.DanceRobootBuilder;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public class ClientTest {
    public static void main(String[] args) {
        RebootDirector director=new RebootDirector();
        Robot robot = director.HumanCreateRobotByDirector(new DanceRobootBuilder());
        System.out.println(robot);
    }
}
