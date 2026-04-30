package com.aaa.javabase.arthas;

/**
 * <pre>
 * ==================== Java Agent 五步走 ====================
 *
 * 【步骤1：编译】
 * cd F:\WorkSpace\MyGithub\java-base\src\test\java\com\aaa\javabase\arthas
 * javac -encoding UTF-8 RetransformAgent.java
 *
 * 【步骤2：打包】
 * jar cvfm agent.jar MANIFEST.MF RetransformAgent*.class
 *
 * 【步骤4：确保目标类已加载】
 * 先访问一次目标方法，让类加载到JVM：
 * http://localhost:8080/httpController/arthasTestServiceSayHello
 *
 * 【步骤5：Attach】
 * & "C:\Program Files\Java\jdk1.8.0_251\bin\javac.exe" -encoding UTF-8 -cp "C:\Program Files\Java\jdk1.8.0_251\lib\tools.jar" AttachMain.java
 * & "C:\Program Files\Java\jdk1.8.0_251\bin\java.exe" -cp ".;C:\Program Files\Java\jdk1.8.0_251\lib\tools.jar" AttachMain 20400 "F:\WorkSpace\MyGithub\java-base\src\test\java\com\aaa\javabase\arthas\agent.jar" "F:/DevlopSoftware/arthas/tempData/com/aaa/javabase/service/ArthasTestService.class"
 *
 * </pre>
 * 这就是 Arthas 的核心原理
 * Arthas 本质上就是：
 * 打包一个 Agent Jar
 * Attach 到目标 Java 进程
 * 在目标进程中执行字节码增强/重定义
 * </pre>
 *
 * @author liuzhen.tian
 * @version 1.0 RetransformAgent.java  2026/4/30 23:57
 */

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.ProtectionDomain;

public class RetransformAgent {

    // 存放从外部传入的新字节码
    private static byte[] newBytecode;

    /**
     * Agent 入口方法（区别于 premain，这是动态 attach 时调用的）
     *
     * @param agentArgs attach 时传入的参数（class 文件路径）
     * @param inst      Instrumentation 实例，核心API
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("=== Agent attached 成功 ===");
        System.out.println("Agent参数: " + agentArgs);

        try {
            // 1. 读取新的字节码文件（从参数获取路径）
            String classFilePath = agentArgs;
            if (classFilePath == null || classFilePath.isEmpty()) {
                System.err.println("❌ agentArgs 为空，请提供 class 文件路径");
                return;
            }

            System.out.println("正在读取字节码文件: " + classFilePath);
            newBytecode = Files.readAllBytes(Paths.get(classFilePath));
            System.out.println("读取字节码成功，大小: " + newBytecode.length + " bytes");

            // 2. 注册 Transformer（返回新字节码）
            inst.addTransformer(new MyTransformer(), true);
            System.out.println("Transformer 注册成功");

            // 3. 查找已加载的目标类
            String targetClassName = "com.aaa.javabase.service.ArthasTestService";
            System.out.println("正在查找已加载的目标类: " + targetClassName);

            Class<?> targetClass = null;
            Class[] loadedClasses = inst.getAllLoadedClasses();
            for (Class<?> cls : loadedClasses) {
                if (cls.getName().equals(targetClassName)) {
                    targetClass = cls;
                    System.out.println("找到目标类: " + cls.getName() + ", ClassLoader: " + cls.getClassLoader());
                    break;
                }
            }

            if (targetClass == null) {
                System.err.println("❌ 未找到已加载的目标类: " + targetClassName);
                System.err.println("提示: 需要先调用一次该类的任意方法，让类被加载到JVM");
                return; // 不抛异常，直接返回
            }

            // 4. 执行 retransform（触发 Transformer 返回新字节码）
            System.out.println("开始执行 retransformClasses...");
            inst.retransformClasses(targetClass);

            System.out.println("✅ retransformClasses 执行成功！");

        } catch (Exception e) {
            System.err.println("❌ Agent 执行失败:");
            e.printStackTrace();
            // 不要抛出异常，避免 AgentInitializationException
        }
    }

    /**
     * 自定义 Transformer
     * 当 retransformClasses 被调用时，会遍历所有已注册的 Transformer
     */
    static class MyTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader,
                                String className,
                                Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain,
                                byte[] classfileBuffer) {

            // 匹配目标类（注意格式：com/aaa/javabase/service/ArthasTestService）
            if ("com/aaa/javabase/service/ArthasTestService".equals(className)) {
                System.out.println("🔧 Transformer 被调用，返回新字节码");
                return newBytecode;  // 返回新的字节码替换原类
            }

            // 其他类不变
            return null;
        }
    }
}
