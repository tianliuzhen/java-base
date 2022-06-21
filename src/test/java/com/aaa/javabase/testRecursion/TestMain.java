package com.aaa.javabase.testRecursion;

import com.aaa.javabase.testRecursion.domains.SearchOrder;
import com.alibaba.fastjson.JSONObject;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/6/16 21:07
 */
public class TestMain {
    public static void main(String[] args) {
        // 模拟一个异常问题
        String strJson = "{\n" +
                "  \"searchOrderName\": \"one\",\n" +
                "  \"features\": {\n" +
                "    \"featuresName\": \"two\",\n" +
                "    \"searchOrder\": {\n" +
                "      \"searchOrderName\": \"one\"\n" +
                "    }\n" +
                "  }\n" +
                "}\n";

        // getAllObject(strJson); // java.lang.StackOverflBeanPostProcessorowError


        // 限制递归层数为2层
        SearchOrder allObjectFix = getAllObjectFix(strJson, 0);

        System.out.println(allObjectFix);
    }

    /**
     * 限制递归层数为2层
     *
     * @param strJson
     * @param count
     * @return
     */
    public static SearchOrder getAllObjectFix(String strJson, int count) {
        SearchOrder searchOrder = new SearchOrder();
        JSONObject jsonObject = JSONObject.parseObject(strJson);
        JSONObject features = jsonObject.getJSONObject("features");
        ++count;
        int tempCount = count;
        features.forEach((k, v) -> {
            // 递归超过俩层返回
            if (tempCount > 2) {
                return;
            }
            // 如果属性是 String
            if (v instanceof String) {
                searchOrder.setSearchOrderName(String.valueOf(v));
                // 如果属性是 对象类型
            } else {
                SearchOrder.Features featuresChildren = new SearchOrder.Features();
                if ("one".equals(features.getJSONObject(k).get("searchOrderName"))) {
                    SearchOrder allObject = getAllObjectFix(strJson, tempCount);
                    featuresChildren.setSearchOrder(allObject);
                }
                searchOrder.setFeatures(featuresChildren);
            }
        });

        return searchOrder;
    }

    /**
     * 这个递归是错的，没有返回返回条件，会无限递归。
     *
     * @param strJson
     * @return
     */
    public static SearchOrder getAllObject(String strJson) {
        SearchOrder searchOrder = new SearchOrder();
        JSONObject jsonObject = JSONObject.parseObject(strJson);
        JSONObject features = jsonObject.getJSONObject("features");
        features.forEach((k, v) -> {
            if (v instanceof String) {
                searchOrder.setSearchOrderName(String.valueOf(v));
            } else {
                SearchOrder.Features featuresChildren = new SearchOrder.Features();
                if ("one".equals(features.getJSONObject(k).get("searchOrderName"))) {
                    SearchOrder allObject = getAllObject(strJson);
                    featuresChildren.setSearchOrder(allObject);
                }
                searchOrder.setFeatures(featuresChildren);
            }
        });

        return searchOrder;
    }


}
