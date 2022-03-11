package com.aaa.javabase.pattern.structure.decoration.demo3.imp;

/**
 * @author liuzhen.tian
 * @version 1.0 WallBeautifyHang.java  2022/3/11 22:49
 */

import com.aaa.javabase.pattern.structure.decoration.demo3.WallBeautify;
import com.aaa.javabase.pattern.structure.decoration.demo3.WallBeautifyDecorator;

/**
 * 墙面装修装饰器角色实现（挂壁画）
 */
public class WallBeautifyHang extends WallBeautifyDecorator {

    public WallBeautifyHang(WallBeautify wallBeautify) {
        super(wallBeautify);
    }

    @Override
    public void decoration() {
        System.out.println("开始挂壁画");
    }

}
