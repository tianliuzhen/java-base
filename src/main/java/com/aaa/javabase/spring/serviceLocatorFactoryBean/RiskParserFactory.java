package com.aaa.javabase.spring.serviceLocatorFactoryBean;

import com.aaa.javabase.spring.serviceLocatorFactoryBean.model.RiskParserEnum;

/**
 * @author liuzhen.tian
 * @version 1.0 RiskParserFactory.java  2024/2/3 22:07
 */
public interface RiskParserFactory {
    RiskParser getParser(RiskParserEnum riskParserEnum);
}
