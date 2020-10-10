package com.aaa.javabase.pattern.structure.decoration.demo2.type;

import com.aaa.javabase.pattern.structure.decoration.demo2.Coffee;

/**
 * 咖啡的种类 - 浓缩咖啡
 * @author liuzhen.tian
 * @version 1.0 ShortBlack.java  2020/10/10 14:31
 */
public class ShortBlack  extends Coffee {

    public ShortBlack() {
        super.setDescription("浓缩");
        super.setPrice(5f);
    }
}
