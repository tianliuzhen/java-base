package com.aaa.javabase.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * @author liuzhen.tian
 * @version 1.0 TestFastJson.java  2022/8/7 21:12
 */
public class TestFastJson {
    private static Word word;

    private static void init() {
        word = new Word();
        word.setA("a");
        word.setB(2);
        word.setC(true);
        word.setD("d");
        word.setE("");
        word.setF(null);
        word.setDate(new Date());

        List<User> list = new ArrayList<User>();
        User user1 = new User(1, "李白", "唐朝");
        User user2 = new User(2, "关羽", "三国");
        list.add(user1);
        list.add(user2);
        list.add(null);
        word.setList(list);

        Map<String, Object> map = new HashMap<>();
        map.put("mapA", "mapa");
        map.put("user1", user1);
        map.put("user4", null);
        map.put("list", list);
        word.setMap(map);
    }

    @Data
    @AllArgsConstructor
    public static class User {
        private int id;
        private String name;
        private String add;
    }

    @Data
    public static class Word {
        private String d;
        private String e;
        private String f;
        private String a;
        private int b;
        private boolean c;
        private Date date;
        private Map<String, Object> map;
        private List<User> list;
    }

    public static void main(String[] args) {
        init();

        // useSingleQuotes();
        // writeMapNullValue();
        // useISO8601DateFormat();
        // writeNullListAsEmpty();
        writeNullStringAsEmpty();
        // sortField();
        // prettyFormat();
        // writeDateUseDateFormat();
        // beanToArray();
        // showJsonBySelf();
    }

    /**
     * 1: UseSingleQuotes:使用单引号而不是双引号,默认为false
     */
    private static void useSingleQuotes() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置useSingleQuotes后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseSingleQuotes));
    }

    /**
     * 2:WriteMapNullValue:是否输出值为null的字段,默认为false
     */
    private static void writeMapNullValue() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置WriteMapNullValue后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 3:UseISO8601DateFormat:Date使用ISO8601格式输出，默认为false
     */
    private static void useISO8601DateFormat() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置UseISO8601DateFormat后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseISO8601DateFormat));
    }

    /**
     * 4:
     * WriteNullListAsEmpty:List字段如果为null,输出为[],而非null
     * 需要配合WriteMapNullValue使用，现将null输出
     */
    private static void writeNullListAsEmpty() {
        word.setList(null);
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置WriteNullListAsEmpty后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteNullListAsEmpty));
        // System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty));

    }

    /**
     *  5:
     *  WriteNullStringAsEmpty:字符类型字段如果为null,输出为"",而非null
     *  需要配合WriteMapNullValue使用，现将null输出
     */
    private static void writeNullStringAsEmpty() {
        word.setE(null);
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置WriteMapNullValue后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteMapNullValue));
        System.out.println("设置WriteMapNullValue、WriteNullStringAsEmpty后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteNullStringAsEmpty));
        // System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
    }

    /**
     * SortField:按字段名称排序后输出。默认为false
     * 这里使用的是fastjson：为了更好使用sort field martch优化算法提升parser的性能，fastjson序列化的时候，
     * 缺省把SerializerFeature.SortField特性打开了。
     * 反序列化的时候也缺省把SortFeidFastMatch的选项打开了。
     * 这样，如果你用fastjson序列化的文本，输出的结果是按照fieldName排序输出的，parser时也能利用这个顺序进行优化读取。
     * 这种情况下，parser能够获得非常好的性能。
     */
    private static void sortField() {
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.SortField));
    }

    /**
     * 7:
     * PrettyFormat
     */
    private static void prettyFormat() {
        word.setMap(null);
        word.setList(null);
        System.out.println(JSON.toJSONString(word));
        System.out.println("设置prettyFormat后：");
        System.out.println(JSON.toJSONString(word, SerializerFeature.PrettyFormat));
    }

    /**
     * 8:
     * WriteDateUseDateFormat:全局修改日期格式,默认为false。
     */
    private static void writeDateUseDateFormat() {
        word.setMap(null);
        word.setList(null);
        System.out.println(JSON.toJSONString(word));
        // 默认格式是：yyyy-MM-dd HH:mm:ss
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        System.out.println(JSON.toJSONString(word, SerializerFeature.WriteDateUseDateFormat));
    }

    /**
     * 8:
     * 将对象转为array输出
     */
    private static void beanToArray() {
        word.setMap(null);
        word.setList(null);
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.BeanToArray));
    }

    /**
     * 9:自定义（组合应用）
     * 格式化输出
     * 显示值为null的字段
     * 将为null的字段值显示为""
     * DisableCircularReferenceDetect:消除循环引用
     */
    private static void showJsonBySelf() {
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty));
    }

}
