package com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.nontech;

import com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.RequireEvent;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.annotations.RequireProcess;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireActionEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireStateEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.TechTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 初始化 - 创建非需求事件
 *
 * @author liuzhen.tian
 * @version 1.0 CreateRequireEvent.java  2021/12/12 12:37
 */
@RequireProcess(
        techType = TechTypeEnum.NON_TECH,
        currentState = RequireStateEnum.INIT,
        action = RequireActionEnum.CREATE,
        nextState = RequireStateEnum.UNASSIGNED)
@Component
public class CreateNonTechRequireEvent implements RequireEvent {
    @Override
    public void execute(TechTypeEnum typeEnum, String code, Object jsonData) {
        System.out.println("创建【技术】需求...");
    }
}
