package com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.tech;

import com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.RequireEvent;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.annotations.RequireProcess;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireActionEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireStateEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.TechTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 未分配 - 分配需求事件
 *
 * @author liuzhen.tian
 * @version 1.0 DistributionRequireEvent.java  2021/12/12 12:42
 */
@RequireProcess(
        techType = {TechTypeEnum.TECH},
        currentState = RequireStateEnum.UNASSIGNED,
        action = RequireActionEnum.DISTRIBUTION,
        nextState = RequireStateEnum.RUNNING)
@Component
public class DistributionRequireEvent implements RequireEvent {
    @Override
    public void execute(TechTypeEnum typeEnum, String code, Object jsonData) {
        System.out.println("分配【技术】需求...");
    }
}
