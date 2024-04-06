package com.aaa.javabase.spring.autowriedAndResource.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuzhen.tian
 * @version 1.0 MyService.java  2024/4/6 21:33
 */
@Service
public class MyService {
    @Resource()
    @Qualifier("helloV2ServiceImpl")
    private HelloService helloService;
    public void say(){
        helloService.helloWord();
    }
}
