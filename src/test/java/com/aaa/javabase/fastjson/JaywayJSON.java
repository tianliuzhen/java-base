package com.aaa.javabase.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * @author liuzhen.tian
 * @version 1.0 JaywayJSON.java  2025/9/17 20:10
 */
public class JaywayJSON {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testRead() {
        String json = "{\"objs\" : [{\"obj\" : 1411455611975}]}";
        DocumentContext ext = JsonPath.parse(json);
        JsonPath p = JsonPath.compile("$.objs[0].obj");
        ext.set(p, 141145561197333L);
        String author = ext.jsonString();
        System.err.println(author);

    }

    @SneakyThrows
    @Test
    public void testRead2() {
        String str = "{\n" +
                "  \"name\": \"11\",\n" +
                "  \"data\": {\n" +
                "    \"name\": \"1122\"\n" +
                "  }\n" +
                "}";
        Object parse = JSONObject.parse(str);
        Features featuresInfo = JSONObject.parseObject(str, Features.class);

        /*
         * 1. fastjson支持Java POJO对象
         * 2. 不支持复杂的json取值
         */
        Object read1 = JSONPath.eval(featuresInfo, "$.data.infoName");


        Object read3 = JsonPath.read(parse, "$.data.name");
        /*
         * 1. jayway不支持普通的Java POJO对象，如果使用会报错：Expected to find an object with property ['data'] in path $ but found 'com.aaa.javabase.fastjson.JaywayJSON$Features'. This is not a json object according to the JsonProvider: 'com.jayway.jsonpath.spi.json.JsonSmartJsonProvider'.
         * 2. 支持 JSONObject (fastjson)、HashMap、ArrayList 等
         * 3. 支持复杂的json取值
         */
        Object read2 = JsonPath.read(featuresInfo, "$.data.name");

        System.err.println();
    }


    @Test
    public void testWrite() {
        String json = "{\"objs\" : [{\"obj\" : 1411455611975}]}";
        DocumentContext ext = JsonPath.parse(json);
        JsonPath p = JsonPath.compile("$.objs[0].obj");
        ext.set(p, 141145561197333L);
        String author = ext.jsonString();
        System.err.println(author);
    }

    @Data
    public static class Features {
        private String name;

        private FeaturesInfo data;
    }

    public static class FeaturesInfo {
        private String infoName;
    }
}
