package com.aaa.javabase.spring.primary;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author liuzhen.tian
 * @version 1.0 PrimaryConfig.java  2023/4/20 23:34
 */
@Configuration
public class PrimaryConfig {

    /**
     * @Primary 或者 @Qualifier("primaryBean2")
     * 都能实现默认注入，如果俩者都存在，以最近的为主，如 @Qualifier 优先生效
     */

    @Primary
    @Bean
    public PrimaryService primaryBean() {
        return new PrimaryServiceImpl("primaryBean");
    }


    @Bean
    public PrimaryService primaryBean2() {
        return new PrimaryServiceImpl2("primaryBean2");
    }

    @Bean
    public PrimaryApp primaryApp(@Qualifier("primaryBean2") PrimaryService primaryService) {
        return new PrimaryApp(primaryService);
    }
}
