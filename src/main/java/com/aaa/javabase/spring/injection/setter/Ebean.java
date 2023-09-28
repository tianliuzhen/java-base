package com.aaa.javabase.spring.injection.setter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 Ebean.java  2023/9/28 21:59
 */
@Data
@Service
@PropertySource(value = "classpath:pro.properties", encoding = "UTF-8")
public class Ebean {
    // 获取cbean的key属性并且转大写
    @Value("#{cbean.key?.toUpperCase()}")
    private String otherBeanProp = null;

    // 这里加#{} 是为了解析Map类型
    @Value("#{${map_data}}")
    private Map<String, String> weiXinConfig;

    @Autowired
    private Cbean cbean;
}
