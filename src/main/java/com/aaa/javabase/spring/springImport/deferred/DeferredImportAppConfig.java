package com.aaa.javabase.spring.springImport.deferred;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liuzhen.tian
 * @version 1.0 AppConfig.java  2024/12/6 22:35
 */
@Import({MyDeferredImportSelector.class})
@Configuration
public class DeferredImportAppConfig {
}
