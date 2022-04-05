package com.aaa.javabase.pattern.behavior.template;

import com.aaa.javabase.pattern.behavior.template.impl.Link03;
import com.aaa.javabase.pattern.behavior.template.impl.Link05;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/4/5 13:37
 */
public class TestMain {
    public static void main(String[] args) {
        Link03 link03 = new Link03();
        link03.run();

        Link05 link05 = new Link05();
        link05.setLink05IsAlarm(true);
        link05.run();
    }
}
