package com.aaa.javabase.base.gc;

/**
 * @author liuzhen.tian
 * @version 1.0 TestGc.java  2023/7/19 21:51
 */
public class TestIdeaConfig {

    /**
     * VM options：虚拟机参数，每个需要以 -D 或 -X 或 -XX 开头，每个参数使用空格隔开。
     * 用于设置虚拟机相关的参数，如：-Xms768m -Xmx768m -XX:PermSize=64M -XX:MaxPermSize=512m
     * program arguments：
     * 程序参数，每个参数需要以空格隔开。用于java主类中的main方法传递参数
     * environment variables：
     * 环境变量，每个参数需要以分号隔开。顾名思义就是当前机器的环境变量，可以手动添加。
     */
    public static void main(String[] args) {
        System.out.println("=====program arguments：=====");
        for (String arg : args) {
            System.out.println(arg);
        }

        System.out.println("=====vm options：=====");
        // 获取所有的vm参数：Properties properties = System.getProperties();
        // 如： -DmyName=tlz
        System.out.println("myName:" + System.getProperty("myName"));

        System.out.println("=====environment arguments：=====");
        // 获取所有环境变量参数：Map<String, String> map = System.getenv();
        System.out.println("name:" + System.getenv("name"));
    }
}
