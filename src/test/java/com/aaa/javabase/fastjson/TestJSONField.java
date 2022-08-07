package com.aaa.javabase.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 TestJSONField.java  2022/8/7 21:57
 */
public class TestJSONField {
    public static void main(String[] args) {
        TempDto aa = new TempDto("aa", new Date(), new Date());
        System.out.println(JSONObject.toJSONString(aa));
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class TempDto {
        /**
         * 如果希望DTO转换输出的是下划线风格（fastjson默认驼峰风格），请使用:
         */
        @JSONField(name = "service_name")
        private String serviceName;

        @JSONField(format = "yyyyMMdd")
        private Date date;

        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date date1;
    }
}
