package com.aaa.javabase.pattern.behavior.observer;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2021/8/22 18:31
 */
public class TestMain {
    public static void main(String[] args) {
        // 1、定义 Switch
        Switch switchTest = new Switch();

        // 2、添加处理事件
        switchTest.addListener(new SwitchListener() {
            @Override
            public void handleEvent(SwitchEvent switchEvent) {
                // 业务逻辑处理
                System.out.println("switchEvent.getSwitchState() = " + switchEvent.getSwitchState());
            }
        });

        // 3、执行动作
        switchTest.open();
        switchTest.close();
    }
}
