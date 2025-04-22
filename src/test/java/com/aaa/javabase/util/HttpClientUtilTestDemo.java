package com.aaa.javabase.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuzhen.tian
 * @version 1.0 HttpClientUtilTestDemo.java  2024/5/31 23:32
 */

public class HttpClientUtilTestDemo {

    @Test
    public void doGetWithPool() throws Exception {
        Map<String, String> header = new HashMap<String, String>() {{
            put("aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaa"); // 入参格式
            put("bbbbbbbbbbbbbb", "aaaaaaaaaaaaaaaa");             // 出参格式
        }};


        HttpClientUtil.doPostWithNoPool("http://localhost:8080/getPeople", header);

    }

    @Test
    public void doPostWithNoPool() throws Exception {
        Map<String, String> header = new HashMap<String, String>() {{
            put("Content-Type", "application/json;charset=utf-8"); // 入参格式
            put("Accept", "text/plain;charset=utf-8");             // 出参格式
        }};

        JSONObject jsonObject = HttpClientUtil.doPostWithNoPool(
                "http://localhost:8080/postTest", null, header);

    }

    @Test
    public void sseEmitter() {
        String sseUrl = "http://localhost:8080/ollama/ai/sseEmitter"; // 替换为你的 SSE 端点

        // 正则表达式匹配 "text" 字段的值
        String regex = "\"text\":\"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);

        StringBuilder res = new StringBuilder();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(sseUrl);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    System.err.println("Failed to connect to SSE endpoint: " + response.getStatusLine().getStatusCode());
                    return;
                }

                // 使用 BufferedReader 读取响应流
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.isEmpty()) {
                            // 简单打印接收到的行，实际中需要解析 SSE 格式
                            // System.out.println("Received: " + line);
                            // 在这里解析 SSE 事件，例如检查行是否以 "data:" 开头
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                // 提取并打印 text 的值
                                String textValue = matcher.group(1);
                                res.append(textValue);
                            } else {
                                System.out.println("未找到 text 字段");
                            }
                        }
                    }
                }
            }
            System.out.println("提取的 text 值: " + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
