package com.aaa.javabase.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 HttpClientUtilTest.java  2023/12/15 21:17
 */
public class HttpClientUtilTest {
    private static CloseableHttpClient client;

    public static void main(String[] args) {
        // 连接请求配置
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000 * 60) // 服务器响应时间设置：某个外部接口长时间未响应直接中断
                /*
                 * 连接等待时间：
                 * 假设：MaxConnTotal=6，MaxConnPerRoute=2，设置 connectionRequestTimeout=4s
                 * 条件：当并发10个线程请求时，假设每个接口的执行时间=3s，，
                 * 结果：因为 MaxConnPerRoute=2，所以每次只能并发俩个去，当第二批执行结束，已经是6s了，
                 *      因为此时还有6个线程未执行，当第三次执行时，就会直接异常抛出（ Timeout waiting for connection from pool）
                 */.setConnectionRequestTimeout(1000 * 4).setConnectTimeout(1000 * 60) // 连接某个服务器的时间，譬如：http连接前的“握手沟通”等前置等待时间
                .build();

        // 创建连接池并设置最大连接数和每个路由的最大连接数
        client = HttpClientBuilder.create().setMaxConnTotal(6) // 一次最多接收MaxTotal次请求
                .setMaxConnPerRoute(2) // 一个服务每次能并行接收的请求数量
                .setDefaultRequestConfig(requestConfig).setConnectionTimeToLive(1, TimeUnit.MINUTES).build();

        // 模拟并发10个线程
        for (int i = 0; i < 10; i++) {
            try {
                new Thread(() -> {
                    try {
                        doGetWithPool("http://localhost:8080/httpController/sleep_3s");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
         因为maxConnPerRoute=2，所以接口分俩批执行，每次执行俩个
         >>>:Fri Dec 15 21:44:43 CST 2023
         >>>:Fri Dec 15 21:44:43 CST 2023

         >>>:Fri Dec 15 21:44:46 CST 2023
         >>>:Fri Dec 15 21:44:46 CST 2023

         当第三次执行时就会报错
         Caused by: org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
         */
    }

    public static JSONObject doGetWithPool(String url) throws Exception {
        // 构造HttpGet请求对象
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 发送请求
            response = client.execute(request);
            if (Objects.isNull(response) || Objects.isNull(response.getStatusLine())) {
                throw new RuntimeException("Http响应结果为空");
            }
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("Http请求失败");
            }
            String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            return JSONObject.parseObject(body);
        } catch (Exception e) {
            throw new Exception("HttpClient调用异常", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
}
