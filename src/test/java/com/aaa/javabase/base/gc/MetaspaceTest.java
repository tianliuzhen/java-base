package com.aaa.javabase.base.gc;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author liuzhen.tian
 * @version 1.0 MetaspaceTest.java  2024/5/23 22:22
 */
public class MetaspaceTest extends ClassLoader{
    /**
     * 验证
     * Exception in thread "main" java.lang.OutOfMemoryError: Compressed class space
     *
     * -XX:MaxMetaspaceSize=10m -XX:MetaspaceSize=10m
     */
    public static void main(String[] args) {
        int n = 0;
        MetaspaceTest oomTest = new MetaspaceTest();
        try {
            for (int i = 0; i < 10000; i++) {
                // 创建class对象用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                // 指定版本,修饰名、类名、包名、父类、接口
                classWriter.visit(Opcodes.V1_6,Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                // 返回字节
                byte[] code = classWriter.toByteArray();
                // 类的加载
                oomTest.defineClass("Class" + i, code, 0, code.length);
                n++;
            }
        } catch (ClassFormatError e) {
            e.printStackTrace();
        } finally {
            System.out.println("n = " + n);
        }
    }
}
