package com.aaa.javabase.pattern.structure.decoration.demo3.imp;

import com.aaa.javabase.pattern.structure.decoration.demo3.WallBeautify;
import com.aaa.javabase.pattern.structure.decoration.demo3.WallBeautifyDecorator;

/**
 * 墙面装修装饰器角色实现（刮腻子）
 *
 * @author liuzhen.tian
 * @version 1.0 WallBeautifyPutty.java  2022/3/11 22:47
 */
public class WallBeautifyPutty extends WallBeautifyDecorator {

    public WallBeautifyPutty(WallBeautify wallBeautify) {
        super(wallBeautify);
    }

    @Override
    public void decoration() {
        System.out.println("开始刮腻子");
    }

}
