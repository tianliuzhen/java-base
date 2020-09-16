package com.aaa.javabase.spring.autowriedAndResource.service;

import org.springframework.stereotype.Service;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/6/16
 */
@Service
public class HelloV2ServiceImpl implements HelloService {
    @Override
    public void helloWord() {
        System.out.println("HelloV2ServiceImpl");
    }
}
