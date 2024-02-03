package com.aaa.javabase.spring.serviceLocatorFactoryBean.impl;

import com.aaa.javabase.spring.serviceLocatorFactoryBean.RiskParser;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 CodingRiskParser.java  2024/2/3 22:02
 */
@Component
public class CodingRiskParser implements RiskParser {
    @Override
    public void scan() {
        System.out.println("编码军规扫描...");
    }
}
