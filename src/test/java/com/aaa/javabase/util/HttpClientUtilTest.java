package com.aaa.javabase.util;

import com.aaa.javabase.common.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 HttpClientUtilTest.java  2023/12/15 21:17
 */
public class HttpClientUtilTest {
    private static CloseableHttpClient client;

    public static void main(String[] args) {
        // testRequestTimeout();

        testMaxConnTotal();
    }

    private static void testRequestTimeout() {
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
                        JSONObject jsonObject = doGetWithPool("http://localhost:8080/httpController/sleep_3s");
                        System.out.println(">>>8080:" + com.aaa.javabase.common.util.DateUtil.transferToTarget(new Date(), com.aaa.javabase.common.util.DateUtil.YYYY_MM_DD_HH_DD_SS) + ":" + jsonObject.toString());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * >>>8080:Sat Dec 16 21:36:23 CST 2023:{"result":"true"}
         * >>>8080:Sat Dec 16 21:36:23 CST 2023:{"result":"true"}
         *
         * Caused by: org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
         */
    }

    private static void testMaxConnTotal() {
        // 连接请求配置
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000 * 60) // 服务器响应时间设置：某个外部接口长时间未响应直接中断
                .setConnectionRequestTimeout(1000 * 40).setConnectTimeout(1000 * 60) // 连接某个服务器的时间，譬如：http连接前的“握手沟通”等前置等待时间
                .build();

        // 创建连接池并设置最大连接数和每个路由的最大连接数
        client = HttpClientBuilder.create().setMaxConnTotal(3) // 一次最多接收MaxTotal次请求
                .setMaxConnPerRoute(2) // 一个服务每次能并行接收的请求数量
                .setDefaultRequestConfig(requestConfig).setConnectionTimeToLive(1, TimeUnit.MINUTES).build();

        // 模拟并发10个线程
        for (int i = 0; i < 8; i++) {
            try {
                new Thread(() -> {
                    try {
                        JSONObject jsonObject = doGetWithPool("http://localhost:8080/httpController/sleep_1s");
                        System.out.println(">>>8080:" + com.aaa.javabase.common.util.DateUtil.transferToTarget(new Date(), com.aaa.javabase.common.util.DateUtil.YYYY_MM_DD_HH_DD_SS) + ":" + jsonObject.toString());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
                new Thread(() -> {
                    try {
                        // 局域网ip，也算单独的区别于127.0.0.1的ip
                        // JSONObject jsonObject = doGetWithPool("http://192.168.10.101:8080/httpController/sleep_1s");
                        JSONObject jsonObject = doGetWithPool("http://localhost:8081/httpController/sleep_1s_1");
                        System.out.println(">>>8081:" + com.aaa.javabase.common.util.DateUtil.transferToTarget(new Date(), DateUtil.YYYY_MM_DD_HH_DD_SS) + ":" + jsonObject.toString());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
