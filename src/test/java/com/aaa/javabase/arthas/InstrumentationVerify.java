package com.aaa.javabase.arthas;

/**
 * @author liuzhen.tian
 * @version 1.0 InstrumentationVerify.java  2026/4/30 23:40
 */

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class InstrumentationVerify {

    // ========== 目标类 ==========
    static class TargetService {
        public String getMessage() {
            return "原始消息";
        }

        public void printMessage() {
            System.out.println("打印: " + getMessage());
        }
    }

    // ========== 自定义 Transformer ==========
    static class DemoTransformer implements ClassFileTransformer {
        private String name;

        DemoTransformer(String name) {
            this.name = name;
        }

        @Override
        public byte[] transform(ClassLoader loader, String className,
                                Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                                byte[] classfileBuffer) throws IllegalClassFormatException {
            if (className != null && className.contains("TargetService")) {
                System.out.println("  🔄 [" + name + "] transform 被调用");
                System.out.println("     类名: " + className);
                System.out.println("     是否重定义中: " + (classBeingRedefined != null));
            }
            return null; // 返回null表示不修改字节码
        }
    }

    // ========== 模拟的 Instrumentation 实现（简化演示） ==========
    static class MockInstrumentation {
        private java.util.List<ClassFileTransformer> transformers = new java.util.ArrayList<>();

        void addTransformer(ClassFileTransformer transformer, boolean canRetransform) {
            transformers.add(transformer);
            System.out.println("📌 注册 Transformer: " + ((DemoTransformer)transformer).name);
        }

        void retransformClasses(Class<?>... classes) throws Exception {
            System.out.println("\n🔄 执行 retransformClasses...");
            for (Class<?> clazz : classes) {
                System.out.println("   处理类: " + clazz.getSimpleName());
                for (ClassFileTransformer t : transformers) {
                    t.transform(clazz.getClassLoader(),
                            clazz.getName().replace('.', '/'),
                            clazz, null, null);
                }
            }
            System.out.println("   ✅ retransformClasses 完成（所有 Transformer 都已调用）");
        }

        void redefineClasses(ClassDefinition... definitions) throws Exception {
            System.out.println("\n⚠️ 执行 redefineClasses...");
            for (ClassDefinition def : definitions) {
                System.out.println("   直接替换类: " + def.getDefinitionClass().getSimpleName());
                System.out.println("   🚫 绕过了所有 Transformer，其他增强将丢失！");
            }
            System.out.println("   ✅ redefineClasses 完成");
        }
    }

    // ========== 主程序 ==========
    public static void main(String[] args) throws Exception {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║     Instrumentation: redefine vs retransform     ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");

        // 1. 初始化
        MockInstrumentation inst = new MockInstrumentation();

        // 2. 注册多个 Transformer（模拟 SkyWalking、Arthas 等）
        System.out.println("【步骤1】注册多个 Transformer（模拟多个增强工具）");
        inst.addTransformer(new DemoTransformer("SkyWalking"), true);
        inst.addTransformer(new DemoTransformer("Arthas-watch"), true);
        inst.addTransformer(new DemoTransformer("自定义监控"), true);

        // 3. 创建目标对象并调用
        System.out.println("\n【步骤2】创建 TargetService 并首次调用");
        TargetService service = new TargetService();
        service.printMessage();

        // 4. 演示 retransformClasses（会经过所有 Transformer）
        System.out.println("\n【步骤3】执行 retransformClasses");
        inst.retransformClasses(TargetService.class);
        service.printMessage(); // 调用一次，确认运行正常

        // 5. 演示 redefineClasses（绕过所有 Transformer）
        System.out.println("\n【步骤4】执行 redefineClasses（直接替换字节码）");
        System.out.println("   注意：这会覆盖所有 Transformer 的增强效果！");
        // 创建一个假的 ClassDefinition（实际使用需要真实字节码）
        ClassDefinition fakeDef = new ClassDefinition(TargetService.class,  generateValidBytecode());
        inst.redefineClasses(fakeDef);

        // 6. 再次执行 retransformClasses（可以看到 Transformer 又生效了）
        System.out.println("\n【步骤5】redefine 之后再执行 retransformClasses");
        System.out.println("   可以看到所有 Transformer 再次被调用");
        inst.retransformClasses(TargetService.class);

        // 7. 总结
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║                   总结对比                        ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ retransformClasses: 经过所有 Transformer（叠加效果）║");
        System.out.println("║ redefineClasses:    绕过所有 Transformer（覆盖效果）║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        // 额外演示：多个 Transformer 的执行顺序
        System.out.println("\n【附录】验证 Transformer 链式调用顺序");
        System.out.println("执行顺序 = 注册顺序");
        System.out.println("  SkyWalking → Arthas-watch → 自定义监控");
    }

    // 生成合法的字节码
    public static byte[] generateValidBytecode() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get(TargetService.class.getName());

        // 修改方法实现
        CtMethod method = ctClass.getDeclaredMethod("getMessage");
        method.setBody("{ return \"修改后的消息\"; }");

        return ctClass.toBytecode();
    }
}
