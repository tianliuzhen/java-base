package com.aaa.javabase.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author liuzhen.tian
 * @version 1.0 Demo.java  2020/10/9 17:07
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Demo implements Cloneable, Serializable {

    private String name;

    private String value;

    private DemoInternal demoInternal;


    @Override
    public Demo clone() {
        Demo demo = null;
        try {
            demo = (Demo) super.clone(); //浅复制
            // demo.setDemoInternal(demoInternal.clone());
            // 或者是
            demo.demoInternal = demoInternal.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return demo;
    }
}
