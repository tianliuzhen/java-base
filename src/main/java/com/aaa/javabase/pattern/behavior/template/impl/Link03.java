package com.aaa.javabase.pattern.behavior.template.impl;


import com.aaa.javabase.pattern.behavior.template.AbstractCarModel;

/**
 * @author liuzhen.tian
 * @version 1.0 Link03.java  2022/4/5 13:35
 */
public class Link03 extends AbstractCarModel {

    @Override
    public void start() {
        System.out.println("领克03：启动...");
    }

    @Override
    public void stop() {
        System.out.println("领克03：熄火...");
    }

    @Override
    public void alarm() {
        System.out.println("领克03：鸣笛...");
    }
}
