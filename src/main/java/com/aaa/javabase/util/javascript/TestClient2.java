package com.aaa.javabase.util.javascript;


import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 Client.java  2023/4/25 22:36
 */
public class TestClient2 {
    /**
     * 自定义包名，不自定义也行，一般要自定义包名，防止重名
     */
    static String packageName = "com.aaa.ccc";
    /**
     * 固定的断言类名
     */
    static String className = "Assert";
    /**
     * 自定义代码
     */
    static String sourceCode = "package " + packageName + ";\n" +
            "import com.alibaba.fastjson.JSONPath;\n" +
            "\n" +
            "import java.util.Map;\n" +
            "\n" +
            "/**\n" +
            " * 注：Assert 固定类名，不可修改\n" +
            " */\n" +
            "public class Assert {\n" +
            "    /**\n" +
            "     * run 方法是固定方法，不可修改\n" +
            "     *\n" +
            "     * @param map 入参用例的返回值\n" +
            "     * @return 返回值是布尔值\n" +
            "     */\n" +
            "    public Boolean run(Map map) {\n" +
            "        // 处理业务\n" +
            "        Boolean result = (Boolean) JSONPath.eval(map, \"$.result.success\");\n" +
            "        return result;\n" +
            "    }\n" +
            "}";


    public static void main(String[] args) throws Exception {
        Map req = JSONObject.parseObject("{\"result\":{\"data\":[],\"success\":true}}\n", Map.class);
        doRun(req, packageName, className, sourceCode);

        Map req2 = JSONObject.parseObject("{\"result\":{\"data\":[],\"success\":false}}\n", Map.class);
        req2.put("success", false);
        doRun(req2, packageName, className, sourceCode);
    }

    private static void doRun(Map req, String packageName, String className, String sourceCode) throws Exception {
        JdkDynamicCompileClassLoader classLoader = JdkDyClassLoaderHelper.getJdkDynamicCompileClassLoader(
                packageName,
                className,
                sourceCode);

        // 加载指定断言类
        String qualifiedName = packageName + "." + className;
        Class<?> klass = classLoader.loadClass(qualifiedName);
        Method run = klass.getMethod("run", Map.class);
        Object obj = klass.newInstance();

        // 反射调用执行
        Object invoke = run.invoke(obj, req);
        System.out.println("invoke = " + invoke);
    }

}
