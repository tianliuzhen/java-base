package com.aaa.javabase.base.annotations.testImpl;

import com.aaa.javabase.base.annotations.A1;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 HuMan.java  2022/8/15 20:29
 */

@A1
@Component
public class Human {
    public void say(String sentence) {
        System.out.println("Human says:" + sentence);
    }

    public void run() {
        System.out.println("Human runs.");
    }
}
