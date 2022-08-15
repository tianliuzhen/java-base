package com.aaa.javabase.base.annotations.testImpl;

import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 Boy.java  2022/8/15 20:29
 */

@Component
public class Boy extends Man {
    @Override
    public void say(String sentence) {
        System.out.println("Human says:" + sentence);
    }

}
