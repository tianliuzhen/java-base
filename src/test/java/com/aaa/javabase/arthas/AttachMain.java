package com.aaa.javabase.arthas;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.AttachNotSupportedException;
import java.io.IOException;

/**
 * @author liuzhen.tian
 * @version 1.0 AttachMain.java  2026/5/1 0:09
 */
public class AttachMain {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("用法: java AttachMain <pid> <agent.jar路径> [agentArgs]");
            System.out.println("示例: java AttachMain 12345 agent.jar \"F:/path/to/ArthasTestService.class\"");
            return;
        }

        String pid = args[0];           // 目标进程 PID
        String agentJar = args[1];       // agent.jar 路径
        String agentArgs = args.length > 2 ? args[2] : null;

        System.out.println("正在 attach 到进程: " + pid);
        System.out.println("Agent JAR 路径: " + agentJar);
        if (agentArgs != null) {
            System.out.println("Agent 参数: " + agentArgs);
        }

        try {
            // attach 到目标 JVM
            VirtualMachine vm = VirtualMachine.attach(pid);
            System.out.println("✓ Attach 成功");

            // 加载 agent
            System.out.println("正在加载 Agent...");
            vm.loadAgent(agentJar, agentArgs);
            System.out.println("✓ Agent 加载成功！");

            // 分离
            vm.detach();
            System.out.println("✓ 已分离");

        } catch (AttachNotSupportedException e) {
            System.err.println("❌ Attach 不支持: " + e.getMessage());
            System.err.println("可能原因:");
            System.err.println("  1. 目标进程不是 Java 进程");
            System.err.println("  2. 目标进程以不同用户身份运行（需要相同用户或管理员权限）");
            System.err.println("  3. 目标进程已退出");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("❌ IO 错误: " + e.getMessage());
            System.err.println("可能原因:");
            System.err.println("  1. Agent JAR 文件不存在或路径错误");
            System.err.println("  2. 权限不足");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ 发生错误: " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
