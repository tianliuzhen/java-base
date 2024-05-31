package com.aaa.javabase.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 HttpClientUtil.java  2022/8/31 20:45
 */
public class HttpClientUtil {
    private static final Logger logger = LogManager.getLogger(HttpClientUtil.class);

    // 构造HttpClient客户端
    private static CloseableHttpClient client;

    static {
        // 连接请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(1000 * 60) // 服务器响应时间设置：某个外部接口长时间未响应直接中断
                /*
                 * 连接等待时间：
                 * 假设：MaxConnTotal=6，MaxConnPerRoute=2，设置 connectionRequestTimeout=4s
                 * 条件：某个接口的执行时间=3s，执行3次，
                 * 结果：在第3次执行的时候，因为已经耗时6s>4s,会直接异常抛出
                 */
                .setConnectionRequestTimeout(1000 * 60 * 10)
                .setConnectTimeout(1000 * 60) // 连接某个服务器的时间，譬如：http连接前的“握手沟通”等前置等待时间
                .build();

        // 创建连接池并设置最大连接数和每个路由的最大连接数
        client = HttpClientBuilder.create()
                .setMaxConnTotal(6) // 一次最多接收MaxTotal次请求
                .setMaxConnPerRoute(2) // 一个服务每次能并行接收的请求数量
                .setDefaultRequestConfig(requestConfig)
                .setConnectionTimeToLive(1, TimeUnit.MINUTES)
                .build();
    }

    /**
     * 描述：POST提交，采用x-www-form-urlencoded 构建参数，即将表单内的数据转换为键值对，如：name=java&age=23
     *
     * @param url
     * @param map
     */
    public static String doPostWithNoPool(String url, Map<String, String> map) {
        List<NameValuePair> params = new ArrayList<>();
        if (!CollectionUtils.isEmpty(map)) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
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

            // result = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            result = toString(httpEntity, StandardCharsets.UTF_8);
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

    /**
     * GET直接请求发起
     *
     * @param url 请求URL
     * @return 返回对象
     */
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
            // String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            String body = toString(response.getEntity(), StandardCharsets.UTF_8);
            return JSONObject.parseObject(body);
        } catch (Exception e) {
            throw new Exception("HttpClient调用异常", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public static JSONObject doGetWithPool(String url,Map<String,String> header) throws Exception {
        // 构造HttpGet请求对象
        HttpGet request = new HttpGet(url);
        if (!CollectionUtils.isEmpty(header)) {
            header.forEach(request::setHeader);
        }
        CloseableHttpResponse response = null;
        try {
            // 发送请求
            response = client.execute(request);
            if (Objects.isNull(response) || Objects.isNull(response.getStatusLine())) {
                throw new RuntimeException("Http响应结果为空");
            }
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("Http请求失败:"+response.getStatusLine().getStatusCode());
            }
            // String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            String body = toString(response.getEntity(), StandardCharsets.UTF_8);
            return JSONObject.parseObject(body);
        } catch (Exception e) {
            throw new Exception("HttpClient调用异常:"+e.getMessage(), e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }


    /**
     * 重写此方法，
     * 因为此方法默认读取的是传回来的 编码，如果有编码就不用我们的编码，如果出现iso-8859-1会导致中文乱码的问题
     * org.apache.http.util.EntityUtils#toString(org.apache.http.HttpEntity, java.nio.charset.Charset)
     *
     * @param entity
     * @param defaultCharset
     * @return
     * @throws Exception
     */
    public static String toString(
            final HttpEntity entity, final Charset defaultCharset) throws Exception {
        Args.notNull(entity, "Entity");
        ContentType contentType = null;
        try {
            // todo 不再取返回的编码类型
            // contentType = ContentType.get(entity);
        } catch (final UnsupportedCharsetException ex) {
            if (defaultCharset == null) {
                throw new UnsupportedEncodingException(ex.getMessage());
            }
        }
        if (contentType != null) {
            if (contentType.getCharset() == null) {
                contentType = contentType.withCharset(defaultCharset);
            }
        } else {
            contentType = ContentType.DEFAULT_TEXT.withCharset(defaultCharset);
        }
        return toString(entity, contentType);
    }

    private static String toString(
            final HttpEntity entity,
            final ContentType contentType) throws IOException {
        final InputStream inStream = entity.getContent();
        if (inStream == null) {
            return null;
        }
        try {
            Args.check(entity.getContentLength() <= Integer.MAX_VALUE,
                    "HTTP entity too large to be buffered in memory");
            int capacity = (int)entity.getContentLength();
            if (capacity < 0) {
                capacity = 4096;
            }
            Charset charset = null;
            if (contentType != null) {
                charset = contentType.getCharset();
                if (charset == null) {
                    final ContentType defaultContentType = ContentType.getByMimeType(contentType.getMimeType());
                    charset = defaultContentType != null ? defaultContentType.getCharset() : null;
                }
            }
            if (charset == null) {
                charset = HTTP.DEF_CONTENT_CHARSET;
            }
            final Reader reader = new InputStreamReader(inStream, charset);
            final CharArrayBuffer buffer = new CharArrayBuffer(capacity);
            final char[] tmp = new char[1024];
            int l;
            while((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } finally {
            inStream.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "1");
        hashMap.put("name", "tom");
        String url = "http://localhost:8080/httpController/req3?params=id";

        // doPost(url, hashMap);

        doGetWithPool("http://localhost:8080/httpController/req5");

        System.out.println();
    }
}
