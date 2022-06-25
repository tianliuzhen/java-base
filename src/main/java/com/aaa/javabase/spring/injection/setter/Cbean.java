package com.aaa.javabase.spring.injection.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 Abean.java  2020/11/25 11:46
 */
@Component
public class Cbean {

    private Dbean dbean;

    @Autowired
    public void setDbean(Dbean dbean) {
        this.dbean = dbean;
    }

    public void get() {
        dbean.print();
    }

    public void print() {
        System.out.println("i am Cbean ...");
    }


}
