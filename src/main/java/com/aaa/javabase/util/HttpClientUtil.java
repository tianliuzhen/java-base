package com.aaa.javabase.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
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
    public static String sendPostByUrlEncoder(String url, Map<String, String> map) {
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpResponse response = null;
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpClient httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity httpEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("输出参数为：" + result);
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "1");
        hashMap.put("name", "tom");
        String url="http://localhost:8080/httpController/req1";
        sendPostByUrlEncoder(url,hashMap);
    }
}
