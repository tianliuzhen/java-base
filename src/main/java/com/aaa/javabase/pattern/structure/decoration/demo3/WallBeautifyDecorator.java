package com.aaa.javabase.pattern.structure.decoration.demo3;

/**
 * 墙面装修装饰器角色
 *
 * @author liuzhen.tian
 * @version 1.0 WallBeautifyDecorator.java  2022/3/11 22:45
 */
public  abstract class WallBeautifyDecorator implements WallBeautify {
    /**
     * 持有一个 Component 对象实例
     */
    private WallBeautify wallBeautify;

    public WallBeautifyDecorator(WallBeautify wallBeautify) {
        this.wallBeautify = wallBeautify;
    }

    @Override
    public void operation() {
        // wallBeautify.operation();
        decoration();
    }

    /**
     * 装饰器实现类自定义实现方法
     *
     * 覆写原操作方法，在原操作之后再进行装饰，
     * 所以需要提供一个抽象的 decoration 方法供不同的装饰器的实现类去实现。
     */
    public abstract void decoration();

}
