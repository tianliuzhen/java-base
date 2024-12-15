package com.aaa.javabase.pattern.behavior.strategy.demo2.model.enums;

import com.aaa.javabase.pattern.behavior.strategy.demo2.model.GroovyScriptToolMeta;
import com.aaa.javabase.pattern.behavior.strategy.demo2.model.HttpToolMeta;
import com.aaa.javabase.pattern.behavior.strategy.demo2.model.ToolMeta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author liuzhen.tian
 * @version 1.0 ToolTypeEnum.java  2024/12/15 14:13
 */
@Getter
@AllArgsConstructor
public enum ToolTypeEnum {
    http("http", HttpToolMeta.class),
    groovyScript("groovyScript", GroovyScriptToolMeta.class);
    private String type;
    private Class<? extends ToolMeta> metaClass;

    public static ToolTypeEnum getByType(String code) {
        return Arrays.stream(ToolTypeEnum.values())
                .filter(e -> StringUtils.equals(e.getType(), code))
                .findAny()
                .orElseThrow(() -> new RuntimeException("枚举值无法匹配"));
    }
}
