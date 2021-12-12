package com.aaa.javabase.pattern.behavior.stateMachine.demo1;

import com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.RequireEvent;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireActionEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireStateEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.TechTypeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * 状态机行为
 *
 * @author liuzhen.tian
 * @version 1.0 RequireStateMachineTrans.java  2021/12/12 12:47
 */
@Data
@Builder
public class RequireStateMachineTrans {

    /**
     * 技术类型
     */
    private TechTypeEnum[] techType;

    /**
     * 当前状态
     */
    private RequireStateEnum currentState;
    /**
     * 当前状态 => 动作
     */
    private RequireActionEnum action;

    /**
     * 需求执行事件
     */
    private RequireEvent requireEvent;

    /**
     * 下个状态
     */
    private RequireStateEnum nextState;

}
