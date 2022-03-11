package com.aaa.javabase.pattern.structure.decoration.demo3;

import com.aaa.javabase.pattern.structure.decoration.demo3.impl2.WallBeautifyClean;
import com.aaa.javabase.pattern.structure.decoration.demo3.imp.WallBeautifyHang;
import com.aaa.javabase.pattern.structure.decoration.demo3.imp.WallBeautifyPaint;
import com.aaa.javabase.pattern.structure.decoration.demo3.imp.WallBeautifyPutty;

/**
 * 参考:https://mp.weixin.qq.com/s/oXHKvWX13RqJXe5d3u-qOw
 *
 * @author liuzhen.tian
 * @version 1.0 DecoratorTest.java  2022/3/11 22:50
 */
public class MainTest {
    public static void main(String[] args) {
        // 清理墙面
        WallBeautify wallBeautifyClean = new WallBeautifyClean();
        wallBeautifyClean.operation();
        System.out.println("--------------");

        // 刮腻子
        WallBeautify wallBeautifyPutty = new WallBeautifyPutty(wallBeautifyClean);
        wallBeautifyPutty.operation();
        System.out.println("--------------");

        // 涂油漆
        WallBeautify wallBeautifyPaint = new WallBeautifyPaint(wallBeautifyPutty);
        wallBeautifyPaint.operation();
        System.out.println("--------------");

        // 挂壁画
        WallBeautify wallBeautifyHang = new WallBeautifyHang(wallBeautifyPaint);
        wallBeautifyHang.operation();
        System.out.println("--------------");

        System.out.println("=======================分割线===========================");

        // 多层嵌套
        WallBeautify wbh = new WallBeautifyHang(new WallBeautifyPaint(
                new WallBeautifyPutty(new WallBeautifyClean())));
        wbh.operation();
        System.out.println("--------------");
    }
}
