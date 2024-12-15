package com.aaa.javabase.pattern.behavior.strategy.demo2.model;

import lombok.Data;

/**
 * 当执行入口是前端调用，需要把泛型 ToolExecRequest#ToolBaseMetaModel 转成 Object 否则无法接受前端传值
 * 前端动态传值
 *
 * @author liuzhen.tian
 * @version 1.0 ToolExecRequest.java  2024/12/15 13:58
 */
@Data
public class ToolExecWebRequest {

    private String id;

    private String desc;

    private String type;

    private String ToolBaseMetaModelObj;
}
