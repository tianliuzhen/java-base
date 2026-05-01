package com.aaa.javabase.service;


/**
 * @author liuzhen.tian
 * @version 1.0 ArthasTestService.java  2024/7/3 21:39
 */
public interface ArthasTestService {
    default String sayHello(){
        return "{\\\"name\\\":\\\"你好\\\"}\\n\"";
    }
}
