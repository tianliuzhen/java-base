package com.aaa.javabase.pattern.structure.decoration.demo3.imp;

/**
 * @author liuzhen.tian
 * @version 1.0 WallBeautifyPaint.java  2022/3/11 22:48
 */

import com.aaa.javabase.pattern.structure.decoration.demo3.WallBeautify;
import com.aaa.javabase.pattern.structure.decoration.demo3.WallBeautifyDecorator;

/**
 * 墙面装修装饰器角色实现（涂油漆）
 */
public class WallBeautifyPaint extends WallBeautifyDecorator {

    public WallBeautifyPaint(WallBeautify wallBeautify) {
        super(wallBeautify);
    }

    @Override
    public void decoration() {
        System.out.println("开始涂油漆");
    }

}
