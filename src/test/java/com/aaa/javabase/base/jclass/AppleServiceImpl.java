package com.aaa.javabase.base.jclass;

import java.util.HashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 AppleImpl.java  2024/4/9 22:16
 */
public class AppleServiceImpl implements FruitService{
    private HashMap map=new HashMap(){{
        System.out.println("AppleServiceImpl... 初始化");
    }};
}
