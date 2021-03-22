package com.aaa.javabase.jdk8.optional.domain;

import lombok.Data;

import java.util.Optional;

/**
 * @author liuzhen.tian
 * @version 1.0 Person.java  2021/3/22 22:58
 */
@Data
public class Person {
    private String name;
    private Optional<Car> car;

}
