package com.aaa.javabase.spring.conditionBean.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/7
 */
@Data
@NoArgsConstructor
public class People {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     *  城市信息
     */
    private City city;

    private List list;

    public People(String name, Integer age, City city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }
}
