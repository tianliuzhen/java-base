package com.aaa.javabase.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author liuzhen.tian
 * @version 1.0 BeanConvertUtil.java  2023/9/1 21:55
 */
public class BeanConvertUtil {
    public static <T> T beanTo(Object obj, Class<T> tClass) {
        return JSONObject.parseObject(JSONObject.toJSONString(obj), tClass);
    }
}
