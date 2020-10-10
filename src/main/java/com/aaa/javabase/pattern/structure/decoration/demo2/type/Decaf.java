package com.aaa.javabase.pattern.structure.decoration.demo2.type;

import com.aaa.javabase.pattern.structure.decoration.demo2.Coffee;

/**
 * 咖啡的种类 - 无糖咖啡
 * @author liuzhen.tian
 * @version 1.0 Decaf.java  2020/10/10 11:56
 */
public class Decaf  extends Coffee {
    public Decaf() {
        super.setDescription("无糖");
        super.setPrice(1f);
    }

}
