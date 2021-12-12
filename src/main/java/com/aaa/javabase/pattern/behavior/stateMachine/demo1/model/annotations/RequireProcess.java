package com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.annotations;

import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireActionEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireStateEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.TechTypeEnum;

import java.lang.annotation.*;

/**
 * 需求执行流程
 *
 * @author liuzhen.tian
 * @version 1.0 RequireProcess.java  2021/12/12 13:40
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireProcess {

    /**
     * 技术类型
     */
    TechTypeEnum[] techType();

    /**
     * 当前状态
     */
    RequireStateEnum currentState();

    /**
     * 当前状态 => 动作
     */
    RequireActionEnum action();

    /**
     * 下个状态
     */
    RequireStateEnum nextState();
}
