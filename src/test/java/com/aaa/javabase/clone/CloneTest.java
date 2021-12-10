package com.aaa.javabase.clone;

import java.io.IOException;

/**
 * 注意：
 * 如果使用 clone 方法 , 嵌套的对象无需实现 implements Cloneable
 * 如果使用 序列化 方法 ,嵌套的对象均需实现  implements Serializable
 *
 * @author liuzhen.tian
 * @version 1.0 test.java  2020/10/9 17:08
 */
public class CloneTest {

    static {
        System.out.println("静态代码块...");
    }

    {
        System.out.println("构造代码块...");
    }

    public CloneTest() {
        System.out.println("无参构造...");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new CloneTest();

        // List<Demo> clone = new ArrayList();
        // List<Demo> list = new ArrayList();
        // list.add(new Demo("a", "b", new DemoInternal("c", "d")));
        // System.out.println("list = " + list);
        // list.forEach(item -> {
        //     clone.add(item.clone());
        // });
        //
        // clone.get(0).setName("a2");
        // clone.get(0).setDemoInternal(new DemoInternal("c2", "d2"));
        // System.out.println("-----------------------");
        // System.out.println("list = " + list);
        // System.out.println("clone = " + clone);

    }
}
