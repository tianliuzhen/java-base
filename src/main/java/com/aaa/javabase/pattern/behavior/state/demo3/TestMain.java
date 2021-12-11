package com.aaa.javabase.pattern.behavior.state.demo3;

/**
 * @author liuzhen.tian
 * @version 1.0 ZtestMain.java  2021/12/10 21:54
 */
public class TestMain {
    public static void main(String[] args) {
        // mock 前端传来需求id、技术类型、参数，0-基础需求，1-技术需求，2-非技术需求
        int requireId = 123;
        String isTech = "tech";
        String type = "distribution";
        String data = "json数据";

        // 1、获取具体的需求状态对象
        Require require = RequireMap.getValue(isTech, type);

        // 2、使用上下文切换不同的形态
        RequireContext requireContext = new RequireContext();
        requireContext.setRequire(require);

        // 3、执行具体的方法
        requireContext.doAction(requireId, data);
    }

}
