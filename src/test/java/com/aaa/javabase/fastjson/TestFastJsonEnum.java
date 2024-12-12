package com.aaa.javabase.fastjson;

import com.aaa.javabase.pattern.behavior.state.demo3.model.IsTechEnum;
import com.aaa.javabase.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 TestFastJsonEnum.java  2023/7/22 16:27
 */
public class TestFastJsonEnum {

    @Data
    static class MyFjData {
        private String name;
        private IsTechEnum isTechEnum;
    }

    public static void main(String[] args) {
        MyFjData myFjData = new MyFjData();
        myFjData.setIsTechEnum(IsTechEnum.TECH);
        String myFjDataStr = JSON.toJSONString(myFjData);
        MyFjData fjData = JSON.parseObject(myFjDataStr, MyFjData.class);
        System.out.println();
    }

    /**
     * 重复序列化导致反斜杠
     * replace("\\\"", "\"") 表示去除一个斜杠  {\"name\":\"李白\"} => {"name":"李白"}
     * replace("\\\\\"", "\"") 表示去除俩个斜杠  {\\"name\":\\"李白\\"} => {"name":"李白"}
     * replace("\\\\\\\"", "\"") 表示去除三个斜杠 {\\\"name\\\":\\\"李白\\\"} => {"name":"李白"}
     */
    @Test
    public void main2() {
        String ygb = "我是好人";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "李白");
        // 每次执行 toJSONString 都会加一层反斜杠
        String str_1 = JSONObject.toJSONString(map); // {"name":"李白"}
        System.out.println(str_1);
        JSONObject.parseObject(str_1);

        String str_2 = JSONObject.toJSONString(str_1);  // "{\"name\":\"李白\"}"
        String str_2_r = str_2.replace("\\\"", "\""); // 去除转义符 \
        str_2_r = StrUtil.trimChar(str_2_r, '"');// 去除首尾 "
        System.out.println(str_2_r);
        JSONObject.parseObject(str_2_r);

        String str_3 = JSONObject.toJSONString(str_2); // "\"{\\\"name\\\":\\\"李白\\\"}\""
        String str_3_r = str_3.replace("\\\\\\\"", "\"");
        str_3_r = str_3_r.replace("\\\"", "\"");// 去除转义符 \
        str_3_r = StrUtil.trimChar(str_3_r, '"');// 去除首尾 "
        str_3_r = StrUtil.trimChar(str_3_r, '"');// 去除首尾 "
        System.out.println(str_3_r);
        JSONObject.parseObject(str_3_r);
    }
}
