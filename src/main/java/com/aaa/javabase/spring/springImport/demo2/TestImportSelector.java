package com.aaa.javabase.spring.springImport.demo2;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author liuzhen.tian
 * @version 1.0 TestImportSelector.java  2022/10/11 21:36
 */
public class TestImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.aaa.javabase.spring.springImport.demo1.TestBean1"};
    }
}
