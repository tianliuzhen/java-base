package com.aaa.javabase.spring.springImport;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liuzhen.tian
 * @version 1.0 AppConfig.java  2022/10/11 21:24
 */

@Import({ImportBean1.class, TestImportSelector.class, TestImportBeanDefinitionRegistrar.class})
@Configuration
public class AppConfig {

    public AppConfig() {
        System.out.println();
    }
}
