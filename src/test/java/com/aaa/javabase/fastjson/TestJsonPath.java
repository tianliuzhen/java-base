package com.aaa.javabase.fastjson;

import com.aaa.javabase.domain.ConfigVarModel;
import com.aaa.javabase.util.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestJsonPath.java  2022/10/18 21:10
 */
public class TestJsonPath {
    public static void main(String[] args) {
        // 测试取数组
        List<ConfigVarModel> configVars = FileUtil.getConfigVars();
        Object object =JSON.toJSON(configVars);
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
    }
}
