package com.aaa.javabase.base.annotations.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 HumanAspect.java  2022/8/15 20:32
 */

@Aspect
@Component
public class HumanAspect {

    @Around("@within(com.aaa.javabase.base.annotations.A1)")
    public void execute1() {
        System.out.println("within A1");
    }

    // @Before("@target(com.aaa.javabase.base.annotations.A1)")
    // public void execute2() {
    //     System.out.println("target A1");
    // }

    @Before("@within(com.aaa.javabase.base.annotations.A2)")
    public void execute3() {
        System.out.println("within A2");
    }

    // @Before("@target(com.aaa.javabase.base.annotations.A2)")
    // public void execute4() {
    //     System.out.println("A2");
    // }
}
