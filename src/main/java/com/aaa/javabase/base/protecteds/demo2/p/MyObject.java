package com.aaa.javabase.base.protecteds.demo2.p;

import lombok.SneakyThrows;

/**
 * @author liuzhen.tian
 * @version 1.0 MyObject2.java  2020/10/12 14:59
 */

public class MyObject {
    @SneakyThrows
    protected Object clone()  {
        return super.clone();
    }
}
