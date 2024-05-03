package com.aaa.javabase.pattern.creater.singleton.type6;

import lombok.Getter;

/**
 * description: 双重检查[推荐用]
 * Double-Check概念对于多线程开发者来说不会陌生，
 * 如代码中所示，我们进行了两次if (singleton == null)检查，这样就可以保证线程安全了。
 * 这样，实例化代码只用执行一次，后面再次访问时，判断if (singleton == null)，直接return实例化对象。
 * <p>
 * 优点：线程安全；延迟加载；效率较高。
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
@Getter
public class Singleton6 {
    private String name;
    private static volatile Singleton6 singleton6;

    private Singleton6() {

    }

    private Singleton6(String name) {
        this.name = name;
    }

    public static Singleton6 getInstance() {
        if (singleton6 == null) {
            synchronized (Singleton6.class) {
                System.err.println("eee");
                if (singleton6 == null) {
                    singleton6 = new Singleton6("tom");
                }
            }
        }
        return singleton6;
    }
}
