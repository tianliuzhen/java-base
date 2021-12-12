package com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 需求状态
 *
 * @author liuzhen.tian
 * @version 1.0 RequireStateEnum.java  2021/12/12 12:15
 */
@Getter
@AllArgsConstructor
public enum RequireStateEnum {
    INIT("init", "初始化"),
    UNASSIGNED("unassigned", "未分配"),
    // TEMP_SAVE("tempSave", "暂存"),
    RUNNING("running", "进行中"),
    NO_CONFIRMED("no_confirmed", "待确认"),

    // 已结束状态无对应无事件
    OVER("over", "已结束"),

    ;
    private String code;
    private String desc;
}
