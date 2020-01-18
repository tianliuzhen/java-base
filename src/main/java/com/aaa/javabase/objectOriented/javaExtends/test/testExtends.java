package com.aaa.javabase.objectOriented.javaExtends.test;

import com.aaa.javabase.objectOriented.javaExtends.newAnimals.Mouse;
import com.aaa.javabase.objectOriented.javaExtends.newAnimals.Penguin;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */
public class testExtends {
    public static void main(String[] args) {
        Mouse mouse=new Mouse("老鼠",1);
        mouse.eatTest();
        mouse.sleep();

        Penguin penguin=new Penguin("企鹅",2);
        penguin.eat();
        penguin.sleep();
    }
}
