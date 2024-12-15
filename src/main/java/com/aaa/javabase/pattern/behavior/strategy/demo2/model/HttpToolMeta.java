package com.aaa.javabase.pattern.behavior.strategy.demo2.model;

import lombok.Data;

import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 HttpToolMeta.java  2024/12/15 13:52
 */
@Data
public class HttpToolMeta extends ToolMeta {
    private String url;

    private String reqType;

    private Map reqParam;

    private Object reqBody;
}
