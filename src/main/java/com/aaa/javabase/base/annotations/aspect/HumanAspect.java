package com.aaa.javabase.base.annotations.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 HumanAspect.java  2022/8/15 20:32
 */

@Aspect
@Component
@Log4j2
public class HumanAspect {

    @Around("@within(com.aaa.javabase.base.annotations.A1)")
    public Object execute1(ProceedingJoinPoint point) {
        System.out.println("within A1");
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable e) {
           log.error(e);
        }
        return result;
    }

    // @Before("@target(com.aaa.javabase.base.annotations.A1)")
    // public void execute2() {
    //     System.out.println("target A1");
    // }

    @Around("@within(com.aaa.javabase.base.annotations.A2)")
    public Object execute3(ProceedingJoinPoint point) {
        System.out.println("within A1");
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            log.error(e);
        }
        return result;
    }

    // @Before("@target(com.aaa.javabase.base.annotations.A2)")
    // public void execute4() {
    //     System.out.println("A2");
    // }
}
