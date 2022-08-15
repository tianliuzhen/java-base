package com.aaa.javabase.base.annotations.testImpl;

import com.aaa.javabase.base.annotations.A2;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 Man.java  2022/8/15 20:29
 */

@A2
@Component
public class Man extends Human {
    @Override
    public void run() {
        System.out.println("Man runs.");
    }

}
