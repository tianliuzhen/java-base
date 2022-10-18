package com.aaa.javabase.util;

import com.aaa.javabase.domain.ConfigVarModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 FileUtil.java  2022/8/1 21:15
 */
public class FileUtil {
    /**
     * 获取系统 json配置
     */
    public static List<ConfigVarModel> getConfigVars() {
        List<ConfigVarModel> localVars = new ArrayList<>();
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("json/config.json");
            String content = null;
            content = FileUtils.readFileToString(new File(url.getPath()));
            localVars = JSON.parseArray(content, ConfigVarModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return localVars;
    }

    public static JSONObject getByFilePath(String filePath) {
        JSONObject localVars=new JSONObject();
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
            String content = null;
            content = FileUtils.readFileToString(new File(url.getPath()));
            localVars = JSON.parseObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return localVars;
    }

    /**
     * 写入文件
     *
     * @param data
     */
    public static void writeStringToFile(String data) {
        List<ConfigVarModel> localVars = new ArrayList<>();
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("json/config.json");
            String content = null;
            FileUtils.writeStringToFile(new File(url.getPath()), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
