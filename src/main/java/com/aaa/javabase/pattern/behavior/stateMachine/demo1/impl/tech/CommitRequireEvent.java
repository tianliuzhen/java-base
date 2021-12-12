package com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.tech;

import com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.RequireEvent;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.annotations.RequireProcess;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireActionEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireStateEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.TechTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 进行中 - 提交需求事件
 *
 * @author liuzhen.tian
 * @version 1.0 CommitRequireEvent.java  2021/12/12 12:38
 */
@RequireProcess(
        techType = TechTypeEnum.TECH,
        currentState = RequireStateEnum.RUNNING,
        action = RequireActionEnum.COMMIT,
        nextState = RequireStateEnum.NO_CONFIRMED)
@Component
public class CommitRequireEvent implements RequireEvent {
    @Override
    public void execute(TechTypeEnum typeEnum, String code, Object jsonData) {
        System.out.println("提交【技术】需求...");
    }
}
