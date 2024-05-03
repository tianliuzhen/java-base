package com.aaa.javabase.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 PayConfig.java  2024/5/3 22:18
 */
@Data
@Component
public class PayConfig {
    @Value("${wx.config.name}")
    private String wxPayConfigName;
    @Value("${zfb.config.name}")
    private String zfbPayConfigName;
}
