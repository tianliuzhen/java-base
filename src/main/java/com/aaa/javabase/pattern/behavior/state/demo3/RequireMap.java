package com.aaa.javabase.pattern.behavior.state.demo3;

import com.aaa.javabase.pattern.behavior.state.demo3.impl.base.BaseConfirmRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.base.BaseEvaluateRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.nontech.CreateNonTechRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.nontech.TempSaveNonTechRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.tech.CreateTechRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.tech.DistributionTechRequire;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册需求容器map
 *
 * @author liuzhen.tian
 * @version 1.0 RequireMap.java  2021/12/10 22:41
 */
public class RequireMap {

    private static Map<String, Map<String, Require>> map = new HashMap<>(16);

    static {
        // 技术
        HashMap<String, Require> techMap = new HashMap<>();
        techMap.put("create", new CreateNonTechRequire());
        techMap.put("tempSave", new TempSaveNonTechRequire());
        // ...
        map.put("tech", techMap);

        // 非技术
        HashMap<String, Require> nonTechMap = new HashMap<>();
        techMap.put("create", new CreateTechRequire());
        techMap.put("distribution", new DistributionTechRequire());
        // ...
        map.put("nonTech", nonTechMap);


        // 通用（需求确认、评价）
        HashMap<String, Require> baseMap = new HashMap<>();
        baseMap.put("confirm", new BaseConfirmRequire());
        baseMap.put("evaluate", new BaseEvaluateRequire());
        // ...
        map.put("base", nonTechMap);
    }

    /**
     * 获取具体的 Require 对象
     *
     * @param isTech 技术类型
     * @param type   操作类型
     * @return Require
     */
    public static Require getValue(Integer isTech, String type) {
        String key = "";
        switch (isTech) {
            // 通用需求
            case 0:
                key = "base";
                break;
            // 技术需求
            case 1:
                key = "tech";
                break;
            // 非技术需求
            case 2:
                key = "nonTech";
                break;
        }
        Map<String, Require> map = RequireMap.map.get(key);
        return map.get(type);
    }

}
