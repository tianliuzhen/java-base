package com.aaa.javabase.spring.springImport;

import com.aaa.javabase.spring.springImport.demo1.TestBean1;
import com.aaa.javabase.spring.springImport.demo2.TestImportSelector;
import com.aaa.javabase.spring.springImport.demo3.TestImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liuzhen.tian
 * @version 1.0 AppConfig.java  2022/10/11 21:24
 */

@Import({TestBean1.class, TestImportSelector.class, TestImportBeanDefinitionRegistrar.class})
@Configuration
public class AppConfig {

    public AppConfig() {
        System.out.println();
    }
}
