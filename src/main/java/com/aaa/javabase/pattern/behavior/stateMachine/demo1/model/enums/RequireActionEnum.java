package com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 需求状态对应动作枚举
 *
 * @author liuzhen.tian
 * @version 1.0 RequireActionEnum.java  2021/12/12 12:10
 */
@Getter
@AllArgsConstructor
public enum RequireActionEnum {
    CREATE("create", "需求创建"),
    DISTRIBUTION("distribution", "需求分配"),
    COMMIT("commit", "需求提交"),
    CONFIRM("confirm", "需求确认"),
    ;

    private String code;
    private String desc;

}
