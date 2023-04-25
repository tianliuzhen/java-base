package com.aaa.javabase.util.javascript;

import com.google.common.collect.Lists;

import javax.tools.*;
import java.lang.reflect.Method;

/**
 * 参考：https://juejin.cn/post/6844904181623439368
 *
 * @author liuzhen.tian
 * @version 1.0 Client.java  2023/4/25 22:36
 */
public class Client {

    static String SOURCE_CODE = "package com.aaa.javabase.domain;\n" +
            "\n" +
            "public class HelloWord {\n" +
            "    public void sayHello(){\n" +
            "        System.err.println(\"HelloWord!\");\n" +
            "    }\n" +
            "}\n";

    // 编译诊断收集器
    static DiagnosticCollector<JavaFileObject> DIAGNOSTIC_COLLECTOR = new DiagnosticCollector<>();

    public static void main(String[] args) throws Exception {
        // 获取系统编译器实例
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获取标准的Java文件管理器实例
        StandardJavaFileManager manager = compiler.getStandardFileManager(DIAGNOSTIC_COLLECTOR, null, null);
        // 初始化自定义类加载器
        JdkDynamicCompileClassLoader classLoader = new JdkDynamicCompileClassLoader(Thread.currentThread().getContextClassLoader());
        // 初始化自定义Java文件管理器实例
        JdkDynamicCompileJavaFileManager fileManager = new JdkDynamicCompileJavaFileManager(manager, classLoader);
        String packageName = "com.aaa.javabase.domain";
        String className = "HelloWord";
        String qualifiedName = packageName + "." + className;
        // 构建Java源文件实例
        CharSequenceJavaFileObject javaFileObject = new CharSequenceJavaFileObject(className, SOURCE_CODE);
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
                DIAGNOSTIC_COLLECTOR,
                null,
                null,
                Lists.newArrayList(javaFileObject)
        );
        // 执行编译任务
        Boolean result = compilationTask.call();
        System.out.println(String.format("编译[%s]结果:%s", qualifiedName, result));

        // 执行方法
        Class<?> klass = classLoader.loadClass(qualifiedName);
        Method sayHello = klass.getMethod("sayHello");
        sayHello.invoke(klass.newInstance());
    }
}
