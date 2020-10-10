package com.aaa.javabase.pattern.structure.decoration.demo2.type2;

import com.aaa.javabase.pattern.structure.decoration.demo2.Decorator;
import com.aaa.javabase.pattern.structure.decoration.demo2.Drink;

/**
 * 咖啡的调料 - 巧克力
 * @author liuzhen.tian
 * @version 1.0 Chocolate.java  2020/10/10 14:42
 */
public class Chocolate extends Decorator {

    public Chocolate(Drink Obj) {
        super(Obj);
        super.setDescription("巧克力");
        super.setPrice(6.0f);
    }

}
