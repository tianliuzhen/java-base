package com.aaa.javabase.pattern.designpattern.createrpatten.singleton.type8;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public enum  Singleton8 {
    SPRING,SUMMER ;
    public void getInstance() {
        System.out.println("枚举实现单例模式");
    }
}
