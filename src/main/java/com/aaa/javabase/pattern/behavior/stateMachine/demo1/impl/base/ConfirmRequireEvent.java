package com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.base;

import com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.RequireEvent;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.annotations.RequireProcess;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireActionEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireStateEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.TechTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 待确认 - 确认需求事件
 *
 * @author liuzhen.tian
 * @version 1.0 ConfirmRequireEvent.java  2021/12/12 12:40
 */

@RequireProcess(
        techType = {TechTypeEnum.TECH, TechTypeEnum.NON_TECH},
        currentState = RequireStateEnum.NO_CONFIRMED,
        action = RequireActionEnum.CONFIRM,
        nextState = RequireStateEnum.OVER)
@Component
public class ConfirmRequireEvent implements RequireEvent {
    @Override
    public void execute(TechTypeEnum typeEnum, String code, Object jsonData) {
        System.out.println("确认【" + typeEnum.getDesc() + "】需求...");
    }
}
