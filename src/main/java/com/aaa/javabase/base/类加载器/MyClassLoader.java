package com.aaa.javabase.base.类加载器;

import java.io.*;

/**
 * @author liuzhen.tian
 * @version 1.0 MyClassLoader.java  2022/7/10 9:50
 */
public class MyClassLoader extends ClassLoader {
    static {
        // 表明当前的ClassLoader可并行加载不同的类
        registerAsParallelCapable();
    }

    /**
     * 指定的字节码类文件所在的本地目录
     */
    private final String commonPath;

    /**
     * 构造函数。默认的parent ClassLoader是 AppClassLoader
     *
     * @param commonPath 字节码类文件所在的本地目录
     */
    public MyClassLoader(String commonPath) {
        if (!commonPath.isEmpty()
                && commonPath.charAt(commonPath.length() - 1) != File.separatorChar) {
            commonPath += File.separator;
        }
        this.commonPath = commonPath;
    }

    /**
     * 构造函数。指定了一个 parent ClassLoader 。
     *
     * @param commonPath 字节码类文件所在的本地目录
     * @param parent     指定的parent ClassLoader
     */
    public MyClassLoader(String commonPath, ClassLoader parent) {
        super(parent);
        if (!commonPath.isEmpty()
                && commonPath.charAt(commonPath.length() - 1) != File.separatorChar) {
            commonPath += File.separator;
        }
        this.commonPath = commonPath;
    }

    /**
     * 覆盖父类的 findClass(..) 方法。
     * 从指定的目录中查找字节码类文件，并创建加载对应的Class对象。
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 读取字节码的二进制流
            byte[] b = loadClassFromFile(name);
            // 调用 defineClass(..) 方法创建 Class 对象
            Class<?> c = defineClass(name, b, 0, b.length);
            return c;
        } catch (IOException ex) {
            throw new ClassNotFoundException(name);
        }
    }

    private byte[] loadClassFromFile(String name) throws IOException {
        String fileName = name.replace('.', File.separatorChar) + ".class";
        String filePath = this.commonPath + fileName;

        try (InputStream inputStream = new FileInputStream(filePath);
             ByteArrayOutputStream byteStream = new ByteArrayOutputStream()
        ) {
            int nextValue;
            while ((nextValue = inputStream.read()) != -1) {
                byteStream.write(nextValue);
            }
            return byteStream.toByteArray();
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.out.println("准备使用 MyClassLoader 加载类：" + name);
        return super.loadClass(name, resolve);
    }
}
