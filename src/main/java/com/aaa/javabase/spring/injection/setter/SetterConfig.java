package com.aaa.javabase.spring.injection.setter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzhen.tian
 * @version 1.0 SetterConfig.java  2023/9/27 21:36
 */

@Configuration
@ComponentScan(value = "com.aaa.javabase.spring.injection.setter")
// @ImportResource(locations = {"classpath:/spring-common.xml"})
public class SetterConfig {

    // @Bean(autowire = Autowire.BY_NAME)
    // public A1bean a1bean() {
    //     return new A1bean();
    // }
    //
    //
    // @Bean(autowire = Autowire.BY_NAME)
    // public A2bean a2bean() {
    //     return new A2bean();
    // }

}


