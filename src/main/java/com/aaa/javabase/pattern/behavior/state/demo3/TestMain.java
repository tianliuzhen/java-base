package com.aaa.javabase.pattern.behavior.state.demo3;

/**
 * @author liuzhen.tian
 * @version 1.0 ZtestMain.java  2021/12/10 21:54
 */
public class TestMain {
    public static void main(String[] args) {
        // 前端拿到，0-基础需求，1-技术需求，2-非技术需求
        int isTech = 1;
        String type = "distribution";

        // 1、获取具体的需求状态对象
        Require require = RequireMap.getValue(isTech, type);

        // 2、使用上下文切换不同的形态
        RequireContext requireContext = new RequireContext();
        requireContext.setRequire(require);

        // 3、执行具体的方法
        requireContext.doAction(123, "sss");
    }

}
