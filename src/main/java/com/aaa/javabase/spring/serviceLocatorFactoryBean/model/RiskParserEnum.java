package com.aaa.javabase.spring.serviceLocatorFactoryBean.model;

import com.aaa.javabase.spring.serviceLocatorFactoryBean.impl.CodingRiskParser;
import com.aaa.javabase.spring.serviceLocatorFactoryBean.impl.MsgRiskParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ClassUtils;

/**
 * @author liuzhen.tian
 * @version 1.0 RiskParserEnum.java  2024/2/3 22:08
 */
@Getter
@AllArgsConstructor
public enum RiskParserEnum {
    codingRiskParser(ClassUtils.getShortNameAsProperty(CodingRiskParser.class), "编码军规扫描"),
    msgRiskParser(ClassUtils.getShortNameAsProperty(MsgRiskParser.class), "消息风险扫描");

    private String code;
    private String desc;
}
