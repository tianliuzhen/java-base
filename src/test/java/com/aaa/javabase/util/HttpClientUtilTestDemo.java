package com.aaa.javabase.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 HttpClientUtilTestDemo.java  2024/5/31 23:32
 */

public class HttpClientUtilTestDemo {

    @Test
    public  void doGetWithPool() throws Exception {
        Map<String, String> header = new HashMap<String, String>() {{
            put("Content-Type", "application/json;charset=utf-8"); // 入参格式
            put("Accept", "text/plain;charset=utf-8");             // 出参格式
        }};


        JSONObject jsonObject = HttpClientUtil.doGetWithPool("http://localhost:8080/getPeople",header);

    }
}
