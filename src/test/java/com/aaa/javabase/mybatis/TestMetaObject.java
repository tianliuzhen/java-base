package com.aaa.javabase.mybatis;


import com.aaa.javabase.domain.ConfigVarModel;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;

import java.util.HashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMetaObject.java  2022/10/18 21:40
 */
public class TestMetaObject {
    public static void main(String[] args) {

        ConfigVarModel configVarModel = new ConfigVarModel();

        Configuration configuration = new Configuration();
        MetaObject metaObject = configuration.newMetaObject(configVarModel);

        // 反射赋值Long ，注意不能是普通类型long，否则无法解析
        metaObject.setValue("id",1L);
        // 反射赋值String
        metaObject.setValue("name","allen");

        // 对应集合类map，需要先创建对象，才能显式赋值
        metaObject.setValue("tag",new HashMap<>());
        metaObject.setValue("tag[one]","1");

        metaObject.getValue("tag[one]");
        // 取值
        System.out.println(configVarModel.toString());

    }
}
