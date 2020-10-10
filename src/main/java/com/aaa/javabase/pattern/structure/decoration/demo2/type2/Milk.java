package com.aaa.javabase.pattern.structure.decoration.demo2.type2;

import com.aaa.javabase.pattern.structure.decoration.demo2.Decorator;
import com.aaa.javabase.pattern.structure.decoration.demo2.Drink;

/**
 *  咖啡的调料 - 牛奶
 * @author liuzhen.tian
 * @version 1.0 Milk.java  2020/10/10 14:43
 */
public class Milk extends Decorator {
    public Milk(Drink Obj) {
        super(Obj);
        super.setDescription("牛奶");
        super.setPrice(8.0f);
    }
}
