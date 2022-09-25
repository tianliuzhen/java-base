package com.aaa.javabase.base.objectOriented.interfaces;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */
public class test {
    public static void main(String[] args) {
        Animal a1=new moneky(1,"猴子");
        a1.eat();
        Animal a2=new dog(1,"狗");
        a2.eat();
    }
}
