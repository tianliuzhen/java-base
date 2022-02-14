package com.aaa.javabase.spring.conditionBean.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private People people;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 城市信息
     */
    private City city;

    private List list;

    private Set set;

    private Map map;

    // 若全局配置已配置，此注解可不加
    // @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private LocalDateTime localDateTime;

    public People(String name, Integer age, City city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }
}
