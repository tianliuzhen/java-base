package com.aaa.javabase.spring.springImport.deferred;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.yuque.com/tianliuzhen/rinavs/iwmzq8vyxr4yfhmg
 *
 * @author liuzhen.tian
 * @version 1.0 MyDeferredImportSelector.java  2024/12/6 22:34
 */
public class MyDeferredImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 不走这里
        return new String[0];
    }

    @Override
    public Class<? extends Group> getImportGroup() {
        // 先走这里
        return MyDeferredImportSelectorGroup.class;
    }

    public static class MyDeferredImportSelectorGroup implements Group {
        private final List<Entry> imports = new ArrayList<>();

        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            // 再走这里
            System.out.println("MyDeferredImportSelectorGroup.Group");
        }

        @Override
        public Iterable<Entry> selectImports() {
            System.out.println("Group中的：selectImports方法");
            return imports;
        }
    }
}
