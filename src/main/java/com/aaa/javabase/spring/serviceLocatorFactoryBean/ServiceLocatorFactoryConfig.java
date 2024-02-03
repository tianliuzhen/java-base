package com.aaa.javabase.spring.serviceLocatorFactoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzhen.tian
 * @version 1.0 BeanConfig.java  2024/2/3 22:11
 */
@Configuration
public class ServiceLocatorFactoryConfig {
    @Bean("riskParserFactory")
    public FactoryBean getRiskParserFactory() {
        ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
        serviceLocatorFactoryBean.setServiceLocatorInterface(RiskParserFactory.class);
        return serviceLocatorFactoryBean;
    }
}
