package com.aaa.javabase.pattern.behavior.state.demo3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 技术和非技术枚举
 *
 * @author liuzhen.tian
 * @version 1.0 IsTechEnum.java  2021/12/11 19:29
 */
@Getter
@AllArgsConstructor
public enum IsTechEnum {

    TECH(1, "tech", "技术"),
    NON_TECH(2, "nonTech", "非技术");

    private Integer value;
    private String code;
    private String desc;
}
