package com.aaa.javabase.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author liuzhen.tian
 * @version 1.0 ArthasTestService.java  2024/7/3 21:39
 */
public interface ArthasTestService {
    default String sayHello(){
        return JSONObject.toJSONString("{\"name\":\"你好\"}\n");
    }
}
