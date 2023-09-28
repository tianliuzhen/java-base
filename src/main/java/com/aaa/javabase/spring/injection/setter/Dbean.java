package com.aaa.javabase.spring.injection.setter;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liuzhen.tian
 * @version 1.0 Bbean.java  2020/11/25 11:47
 */
// @Component
public class Dbean {

    @Autowired
    private Cbean cbean;

    // @Autowired
    public void setCbean(Cbean cbean) {
        this.cbean = cbean;
    }

    public void get(){
        cbean.print();
    }

    public void print(){
        System.out.println("i am Dbean ...");
    }


}
