package com.aaa.javabase.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 ProProperties.java  2023/8/26 23:13
 */

@Data
@Component
// 其他配置资源文件的位置，如果不配置无法读取 pro.properties 文件下的属性
// 如果属性在application.properties 里面默认可以不配置此注解
@PropertySource(value = "classpath:pro.properties", encoding = "UTF-8")
public class ProProperties {

    @Value("${className}")
    private String className;

    @Value("#{${methodName}}")
    private String methodName;

    // 这里加#{} 是为了解析Map类型
    @Value("#{${wei_xin_config}}")
    private Map<String, String> weiXinConfig;
}
