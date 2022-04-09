package com.aaa.javabase.pattern.structure.decoration.demo3.common;

import com.aaa.javabase.pattern.structure.decoration.demo3.WallBeautify;

/**
 * 墙面装修基本实现（清理墙面）
 *
 * @author liuzhen.tian
 * @version 1.0 WallBeautifyClean.java  2022/3/11 22:45
 */
public class WallBeautifyClean implements WallBeautify {
    @Override
    public void operation() {
        System.out.println("开始清理墙面");
    }
}
