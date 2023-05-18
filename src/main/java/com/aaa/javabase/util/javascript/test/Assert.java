// package com.aaa.javabase.util.javascript.test;
//
// import com.alibaba.fastjson.JSONPath;
//
// import java.util.Map;
//
// /**
//  * 注：Assert 固定类名，不可修改
//  */
// public class Assert {
//     /**
//      * run 方法是固定方法，不可修改
//      *
//      * @param map 入参用例的返回值
//      * @return 返回值是布尔值
//      */
//     public Boolean run(Map map) {
//         // jsonpath 获取参数
//         Object result = JSONPath.eval(map, "$.result.success");
//
//         // 比较参数
//         Boolean success = (Boolean) result;
//         return success;
//     }
// }
