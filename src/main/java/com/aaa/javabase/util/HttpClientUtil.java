package com.aaa.javabase.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 HttpClientUtil.java  2022/8/31 20:45
 */
public class HttpClientUtil {
    /**
     * 描述：POST提交，采用x-www-form-urlencoded 构建参数，即将表单内的数据转换为键值对，如：name=java&age=23
     *
     * @param url
     * @param map
     */
    public static String doPost(String url, Map<String, String> map) {
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        CloseableHttpResponse response = null;
        String result = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost httppost = new HttpPost(url);
            httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            response = httpClient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();

            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doClose(response, httpClient);
        }
        System.out.println("输出参数为：" + result);
        return result;
    }

    private static void doClose(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "1");
        hashMap.put("name", "tom");
        String url = "http://localhost:8080/httpController/req1";

        doPost(url, hashMap);
    }
}
