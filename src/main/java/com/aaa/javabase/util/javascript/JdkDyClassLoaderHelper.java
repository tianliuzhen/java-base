package com.aaa.javabase.util.javascript;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 * @author liuzhen.tian
 * @version 1.0 JdkDyClassLoaderHelper.java  2023/5/18 22:35
 */

@Slf4j
public class JdkDyClassLoaderHelper {

    /**
     * 动态加载代码
     *
     * @param packageName 自定义包名
     * @param className   自定义类名
     * @param sourceCode  源代码
     * @return JdkDynamicCompileClassLoader
     */
    public static JdkDynamicCompileClassLoader getJdkDynamicCompileClassLoader(String packageName,
                                                                               String className,
                                                                               String sourceCode) {
        // 获取系统编译器实例
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获取标准的Java文件管理器实例
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        // 初始化自定义类加载器
        JdkDynamicCompileClassLoader classLoader = new JdkDynamicCompileClassLoader(Thread.currentThread().getContextClassLoader());
        // 初始化自定义Java文件管理器实例
        JdkDynamicCompileJavaFileManager fileManager = new JdkDynamicCompileJavaFileManager(manager, classLoader);

        // 构建Java源文件实例
        CharSequenceJavaFileObject javaFileObject = new CharSequenceJavaFileObject(className, sourceCode);
        // 添加Java源文件实例到自定义Java文件管理器实例中
        fileManager.addJavaFileObject(
                StandardLocation.SOURCE_PATH,
                packageName,
                className + CharSequenceJavaFileObject.JAVA_EXTENSION,
                javaFileObject
        );
        // 初始化一个编译任务实例
        JavaCompiler.CompilationTask compilationTask = compiler.getTask(
                null,
                fileManager,
                null,
                null,
                null,
                Lists.newArrayList(javaFileObject)
        );
        // 执行编译任务
        Boolean result = compilationTask.call();
        System.out.println(String.format("编译[%s]结果:%s", packageName + "." + className, result));
        return classLoader;
    }

}
