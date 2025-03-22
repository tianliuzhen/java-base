package com.aaa.javabase.spring.staticBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 WxPayConfiguration.java  2025/3/9 16:54
 */
@Component
public class WxPayConfig {

    public static WxPayConfig wxPayConfig;

    @Autowired
    public void setWxPayConfig(WxPayConfig wxPayConfig) {
        WxPayConfig.wxPayConfig = wxPayConfig;
    }
}
