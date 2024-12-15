package com.aaa.javabase.pattern.behavior.strategy.demo2.actuator;

import com.aaa.javabase.pattern.behavior.strategy.demo2.ChooserActuator;
import com.aaa.javabase.pattern.behavior.strategy.demo2.ToolActuator;
import com.aaa.javabase.pattern.behavior.strategy.demo2.model.GroovyScriptToolMeta;
import com.aaa.javabase.pattern.behavior.strategy.demo2.model.ToolExecRequest;
import com.aaa.javabase.pattern.behavior.strategy.demo2.model.enums.ToolTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 GroovyScriptActuator.java  2024/12/15 14:09
 */
@ChooserActuator(ToolTypeEnum.groovyScript)
@Component
public class GroovyScriptActuator implements ToolActuator<GroovyScriptToolMeta> {
    @Override
    public Object execute(ToolExecRequest<GroovyScriptToolMeta> request) {
        GroovyScriptToolMeta toolBaseMetaModel = request.getToolBaseMetaModel();

        // 执行groovy脚本，返回结果
        return "http request success";
    }
}
