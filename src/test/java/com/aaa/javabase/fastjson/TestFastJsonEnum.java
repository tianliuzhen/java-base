package com.aaa.javabase.fastjson;

import com.aaa.javabase.pattern.behavior.state.demo3.model.IsTechEnum;
import com.alibaba.fastjson.JSON;
import lombok.Data;

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
}
