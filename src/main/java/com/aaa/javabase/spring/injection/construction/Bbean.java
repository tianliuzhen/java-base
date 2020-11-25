package com.aaa.javabase.spring.injection.construction;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 Bbean.java  2020/11/25 11:47
 */
@Component
public class Bbean {

    private Abean abean;

    public Bbean(@Lazy Abean abean) {
        this.abean = abean;
    }

    public void get(){
        abean.print();
    }

    public void print(){
        System.out.println("i am Bbean ...");
    }
}
