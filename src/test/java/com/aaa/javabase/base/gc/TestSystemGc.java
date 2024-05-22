package com.aaa.javabase.base.gc;

/**
 * @author liuzhen.tian
 * @version 1.0 TestSysTemGc.java  2024/5/15 22:25
 */
public class TestSystemGc {
    public static void main(String[] args) throws InterruptedException {
        TestSystemGc testSystemGc = new TestSystemGc();
        testSystemGc = null;

        System.gc(); // 不是马上回收，是通知jvm要回收了

        Thread.sleep(1000); // 如果不加sleep，finalize 可能来不及执行
    }

    // 当一个对象要被回收时，执行
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize ...");
    }
}
