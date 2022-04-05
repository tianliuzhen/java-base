package com.aaa.javabase.pattern.behavior.template.impl;


import com.aaa.javabase.pattern.behavior.template.AbstractCarModel;
import lombok.Setter;

/**
 * @author liuzhen.tian
 * @version 1.0 Link03.java  2022/4/5 13:35
 */
public class Link05 extends AbstractCarModel {

    /**
     * 然后对外提供一个public接口setAlarm来让外界设置这个判断标记，这就像是开关一样，
     * 想让它true和false都行
     */
    @Setter
    private boolean link05IsAlarm;

    @Override
    public void start() {
        System.out.println("领克05：启动...");
    }

    @Override
    public void stop() {
        System.out.println("领克05：熄火...");
    }

    @Override
    public void alarm() {
        System.out.println("领克05：鸣笛...");
    }

    @Override
    protected boolean isAlarm() {
        return this.link05IsAlarm;
    }
}
