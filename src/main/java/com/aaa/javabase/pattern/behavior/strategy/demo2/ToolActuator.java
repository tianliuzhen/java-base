package com.aaa.javabase.pattern.behavior.strategy.demo2;

import com.aaa.javabase.pattern.behavior.strategy.demo2.model.ToolMeta;
import com.aaa.javabase.pattern.behavior.strategy.demo2.model.ToolExecRequest;

/**
 * @author liuzhen.tian
 * @version 1.0 ToolActuator.java  2024/12/15 13:57
 */
public interface ToolActuator <T extends ToolMeta> {
    public Object execute(ToolExecRequest<T> request);
}
