package com.aaa.javabase.base.jclass;

/**
 * @author liuzhen.tian
 * @version 1.0 FruitInteface.java  2024/4/9 22:14
 */
public interface FruitService {
    public final static int num = 0;
    public final static Object obj = new Object() {{
        System.out.println("FruitService... 初始化");
    }};
    // 如果不存在 default 方法，那么创建AppleServiceImpl的时候，不会初始化 FruitService
    public default void syaHello() {
        System.out.println("syaHello...");
    }
}
