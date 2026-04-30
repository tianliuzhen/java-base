import com.sun.tools.attach.VirtualMachine;

/**
 * @author liuzhen.tian
 * @version 1.0 AttachMain.java  2026/5/1 0:09
 */
// import com.sun.tools.attach.VirtualMachine;

public class AttachMain {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("用法: java AttachMain <pid> <agent.jar路径> [agentArgs]");
            return;
        }

        String pid = args[0];           // 目标进程 PID
        String agentJar = args[1];       // agent.jar 路径
        String agentArgs = args.length > 2 ? args[2] : null;

        System.out.println("正在 attach 到进程: " + pid);

        // attach 到目标 JVM
        VirtualMachine vm = VirtualMachine.attach(pid);

        // 加载 agent
        vm.loadAgent(agentJar, agentArgs);

        // 分离
        vm.detach();

        System.out.println("Agent 加载成功！");
    }
}
