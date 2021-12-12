package com.aaa.javabase.pattern.behavior.stateMachine.demo1.impl;

import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.TechTypeEnum;

/**
 * 需求对应的事件
 *
 * @author liuzhen.tian
 * @version 1.0 RequireEvent.java  2021/12/12 12:28
 */
public interface RequireEvent {
    /**
     * 执行事件
     *
     * @param typeEnum 技术类型
     * @param code     需求编号
     * @param jsonData 需求参数
     */
    void execute(TechTypeEnum typeEnum, String code, Object jsonData);
}
