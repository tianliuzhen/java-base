package com.aaa.javabase.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liuzhen.tian
 * @version 1.0 DemoInternal.java  2020/10/9 17:07
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
public class DemoInternal implements Cloneable, Serializable {

    private String internalName;

    private String internalValue;


    @Override
    public DemoInternal clone() throws CloneNotSupportedException {
        return (DemoInternal) super.clone();
    }


}
