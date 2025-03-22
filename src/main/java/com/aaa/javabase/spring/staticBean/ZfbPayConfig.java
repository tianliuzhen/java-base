package com.aaa.javabase.spring.staticBean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author liuzhen.tian
 * @version 1.0 WxPayConfiguration.java  2025/3/9 16:54
 */
@Component
public class ZfbPayConfig implements InitializingBean {

    public static ZfbPayConfig zfbPayConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 这种方式同  @PostConstruct
        // zfbPayConfig = this;
    }

    @PostConstruct
    public void init() {
        zfbPayConfig = this;
    }
}
