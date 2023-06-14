package com.aaa.javabase.spring.springImport;

/**
 * @author liuzhen.tian
 * @version 1.0 TestImportBeanDefinitionRegistrar.java  2022/10/11 21:37
 */

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class TestImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    // @Override
    // public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    //     RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(TestBean3.class);
    //     registry.registerBeanDefinition("testBean3", rootBeanDefinition);
    // }

    // @Override
    // public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    //     BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestBean3.class);
    //     AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    //     registry.registerBeanDefinition("testBean3", beanDefinition);
    // }

    //或者 使用如下的方法也可以，自动生成beanName
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ImportBean3.class);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        String beanName = importBeanNameGenerator.generateBeanName(beanDefinition, registry);
        // 此时的 beanName 是全类名
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
