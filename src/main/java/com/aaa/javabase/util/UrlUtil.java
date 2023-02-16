package com.aaa.javabase.util;

/**
 * Url 常用工具
 *
 * @author liuzhen.tian
 * @version 1.0 Demo02.java  2022/7/18 21:22
 */

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;

public class UrlUtil {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String url = "http://10.19.92.132:8080/wssp/formlistenerservlet?fid=2b95e5b8:13b96f5bdff:-7fff&forminstid=e0827606155a41e29a6470f142e626f3&procinst_id=55550899:157d0434850:-7fd8&urlAppuid=1c9263c0671e48eaa8ea2bc121e9c5a2";
        String url2 = "http://%31%30%2e%31%39%2e%39%32%2e%31%33%32:%38%30%38%30/%77%73%73%70/%66%6f%72%6d%6c%69%73%74%65%6e%65%72%73%65%72%76%6c%65%74?%66%69%64=%32%62%39%35%65%35%62%38:%31%33%62%39%36%66%35%62%64%66%66:%2d%37%66%66%66&%66%6f%72%6d%69%6e%73%74%69%64=%65%30%38%32%37%36%30%36%31%35%35%61%34%31%65%32%39%61%36%34%37%30%66%31%34%32%65%36%32%36%66%33&%70%72%6f%63%69%6e%73%74%5f%69%64=%35%35%35%35%30%38%39%39:%31%35%37%64%30%34%33%34%38%35%30:%2d%37%66%64%38&%75%72%6c%41%70%70%75%69%64=%31%63%39%32%36%33%63%30%36%37%31%65%34%38%65%61%61%38%65%61%32%62%63%31%32%31%65%39%63%35%61%32";

        new URI("xxx");


        Map parse = parse(url2, true);
        System.out.println(parse);
    }

    /**
     * url 转 JSONObject
     *
     * @param url
     * @param decode 是否解密
     * @return
     */
    @SneakyThrows
    public static Map parse(String url, boolean decode) {
        if (decode) {
            // 解码
            url = URLDecoder.decode(url, "UTF-8");
            // URLEncoder.encode(url,"UTF-8");   // 编码
        }
        return parse(url);
    }


    /**
     * url 转 JSONObject
     * <p>
     * 返回：基本路径 baseUrl、参数 param
     * todo：URL中的特殊字符处理问题
     *
     * @param url url
     * @return JSONObject
     */
    public static JSONObject parse(String url) {
        JSONObject jsonObj = new JSONObject();
        if (url == null) {
            return jsonObj;
        }
        url = url.trim();
        if (StringUtils.isBlank(url)) {
            return jsonObj;
        }
        String[] urlParts = url.split("\\?");
        jsonObj.put("baseUrl", urlParts[0]);

        // 没有参数
        if (urlParts.length == 1) {
            return jsonObj;
        }

        // 有参数
        String[] params = urlParts[1].split("&");
        JSONObject paramMap = new JSONObject();
        for (String param : params) {
            String[] keyValue = param.split("=");
            paramMap.put(keyValue[0], keyValue[1]);
            jsonObj.put("param", paramMap);
        }
        return jsonObj;
    }

    public static String getDomainName(String url) {
        String host = "";
        try {
            URL Url = new URL(url);
            host = Url.getHost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return host;
    }
}
