package com.aaa.javabase.fastjson;

import com.aaa.javabase.base.generic.MonitorDataResp;
import com.aaa.javabase.base.generic.WhiteBoxDataModel;
import com.aaa.javabase.domain.ConfigVarModel;
import com.aaa.javabase.util.FileUtil;
import com.alibaba.fastjson.*;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestJsonPath.java  2022/10/18 21:10
 */
public class TestJsonPath {
    public static void main(String[] args) {
        // 测试取数组
        List<ConfigVarModel> configVars = FileUtil.getConfigVars();
        Object object = JSON.toJSON(configVars);
        JSONPath.eval(object, "$[0].id");

        // 测试取对象
        JSONObject byFilePath = FileUtil.getByFilePath("json/other.json");
        JSONPath.eval(byFilePath, "$.result");
        System.out.println();

        // JavaObject 转 泛型：
        String str = JSONObject.toJSONString(configVars);
        JSONArray strArray = JSONArray.parseArray(str);
        ConfigVarModel configVarModel = JSON.toJavaObject(strArray.getJSONObject(0), ConfigVarModel.class);
        System.out.println();

        // json字符串转复杂泛型
        // errorMixDemo();
        successMixDemo();
    }

    /**
     * 泛型对象，无法直接转java对象
     * java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com.aaa.javabase.base.generic.WhiteBoxDataModel
     */
    private static void successMixDemo() {
        String mixStr = JSONObject.toJSONString(getMixData());
        MonitorDataResp<WhiteBoxDataModel> monitorDataResp = JSONObject.parseObject(
                mixStr,
                new TypeReference<MonitorDataResp<WhiteBoxDataModel>>() {
                });
        WhiteBoxDataModel formatResult = monitorDataResp.getFormatResult();
    }

    /**
     * 泛型对象，无法直接转java对象
     * java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com.aaa.javabase.base.generic.WhiteBoxDataModel
     */
    private static void errorMixDemo() {
        String mixStr = JSONObject.toJSONString(getMixData());
        MonitorDataResp<WhiteBoxDataModel> monitorDataResp = JSONObject.parseObject(mixStr, MonitorDataResp.class);
        WhiteBoxDataModel formatResult = monitorDataResp.getFormatResult();
    }

    private static Object getMixData() {
        MonitorDataResp<WhiteBoxDataModel> whiteBoxData = new MonitorDataResp<>();
        WhiteBoxDataModel formatResult = new WhiteBoxDataModel();
        formatResult.setName("aa");
        whiteBoxData.setFormatResult(formatResult);
        return whiteBoxData;
    }
}
