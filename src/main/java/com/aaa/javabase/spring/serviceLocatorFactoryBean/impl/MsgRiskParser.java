package com.aaa.javabase.spring.serviceLocatorFactoryBean.impl;

import com.aaa.javabase.spring.serviceLocatorFactoryBean.RiskParser;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 MsgRiskParser.java  2024/2/3 22:06
 */
@Component
public class MsgRiskParser implements RiskParser {
    @Override
    public void scan() {
        System.out.println("消息风险扫描...");
    }
}
