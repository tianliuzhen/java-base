package com.aaa.javabase.pattern.behavior.strategy.demo2.model;

import lombok.Data;

/**
 * @author liuzhen.tian
 * @version 1.0 ToolExecRequest.java  2024/12/15 13:58
 */

@Data
public class ToolExecRequest<T extends ToolMeta> {

    private String id;

    private String desc;

    private T ToolBaseMetaModel;
}
