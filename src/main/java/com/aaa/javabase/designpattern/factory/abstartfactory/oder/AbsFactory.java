package com.aaa.javabase.designpattern.factory.abstartfactory.oder;

import com.aaa.javabase.designpattern.factory.abstartfactory.pizza.Pizza;

/**
 * description: 一个抽象工厂模式的抽象层（接口）
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/11
 */
public interface AbsFactory {
    //下面的工厂子类具体实现
     Pizza createFactory(String orderType);
}
