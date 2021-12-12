package com.aaa.javabase.pattern.behavior.stateMachine.demo1;

import com.aaa.javabase.pattern.MyBeanFactory;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl.RequireEvent;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.annotations.RequireProcess;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireActionEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireStateEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.TechTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * 状态机
 *
 * @author liuzhen.tian
 * @version 1.0 RequireStatusMachine.java  2021/12/12 12:08
 */
@Component
public class RequireStateMachine {

    @Autowired
    private MyBeanFactory myBeanFactory;

    List<RequireStateMachineTrans> machineTransList = Lists.newArrayList();

    // 初始化：构建状态Action集合
    @PostConstruct
    public void init() {
        List<RequireEvent> beans = myBeanFactory.getBeansOfType(RequireEvent.class);
        // 创建需求  =》分配需求  =》提交需求 =》确认需求
        for (RequireEvent bean : beans) {
            RequireProcess annotation = bean.getClass().getAnnotation(RequireProcess.class);
            machineTransList.add(RequireStateMachineTrans
                    .builder()
                    .techType(annotation.techType())
                    .currentState(annotation.currentState())
                    .requireEvent(bean)
                    .action(annotation.action())
                    .nextState(annotation.nextState())
                    .build());
        }
    }

    @Setter
    @Getter
    private RequireStateEnum state;

    public void execute(TechTypeEnum techTypeEnum, RequireActionEnum action, String code, Object jsonData) {
        // 1、匹配事件
        RequireStateMachineTrans machineTrans = machineTransList
                .stream()
                // 匹配技术类型
                .filter(e -> Arrays.asList(e.getTechType()).contains(techTypeEnum))
                // 匹配状态和动作
                .filter(e -> e.getCurrentState().equals(state) && e.getAction().equals(action))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(action.getCode() + ":找不到处理器"));

        // 2、执行事件
        machineTrans.getRequireEvent().execute(techTypeEnum, code, jsonData);

        // 3、设置下一个需求状态
        this.setState(machineTrans.getNextState());
    }
}
