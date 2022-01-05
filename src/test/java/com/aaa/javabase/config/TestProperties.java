package com.aaa.javabase.config;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author liuzhen.tian
 * @version 1.0 TestProperties.java  2022/1/5 20:03
 */

@Data
// @PropertySource(value = "classpath:application.properties",encoding = "UTF-8")
@ConfigurationProperties(prefix = "test")
@Component
@Validated
public class TestProperties {

    @NotNull(message = "name不能为null")
    private String name;

    @NotNull(message = "correct不能为空")
    private CorrectProperties correct;

    @NotNull(message = "autoCorrectCallBack 不能为空")
    private CallBackProperties autoCorrectCallBack;

    @Data
    public static class CallBackProperties {
        /**
         * 接口地址
         */
        @NotEmpty(message = "配置文件必须含有[url]回调地址")
        private String url;
    }

    @Data
    public static class CorrectProperties {

        @NotEmpty(message = "配置文件必须含有[server]")
        private String server;

        private String appId;

        private String secretKey;
    }
}
