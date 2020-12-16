package com.aaa.javabase.spring.injection.construction;

import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 Abean.java  2020/11/25 11:46
 */
@Component
public class Abean {

    private Bbean bbean;

    // 使用 @Lazy 解决循环依赖问题
    public Abean(Bbean bbean) {
        this.bbean = bbean;
    }

    public void get(){
        bbean.print();
    }

    public void print(){
        System.out.println("i am Abean ...");
    }
}
