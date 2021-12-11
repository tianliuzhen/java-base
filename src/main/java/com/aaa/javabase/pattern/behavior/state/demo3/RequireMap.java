package com.aaa.javabase.pattern.behavior.state.demo3;

import com.aaa.javabase.pattern.behavior.state.demo3.impl.base.BaseConfirmRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.base.BaseEvaluateRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.nontech.CreateNonTechRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.nontech.TempSaveNonTechRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.tech.CreateTechRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.impl.tech.DistributionTechRequire;
import com.aaa.javabase.pattern.behavior.state.demo3.model.IsTechEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 注册需求容器map
 *
 * @author liuzhen.tian
 * @version 1.0 RequireMap.java  2021/12/10 22:41
 */
public class RequireMap {

    // 技术和非技术需求-流程状态处理
    private static Map<String, Map<String, Require>> map = new HashMap<>(16);
    // 技术和非技术需求-通用流程状态处理
    private static Map<String, Require> baseMap = new HashMap<>(16);

    /**
     * 初始化需求状态对应动作
     */
    static {
        // #### 非技术
        HashMap<String, Require> techMap = new HashMap<>();
        // 初始状态 - 对应创建
        techMap.put("create", new CreateNonTechRequire());
        // 初始状态 - 对应暂存
        techMap.put("tempSave", new TempSaveNonTechRequire());
        // ...
        map.put(IsTechEnum.TECH.getCode(), techMap);
        // #### 非技术
        HashMap<String, Require> nonTechMap = new HashMap<>();
        // 初始状态 - 对应创建
        techMap.put("create", new CreateTechRequire());
        // 初始状态 - 分发
        techMap.put("distribution", new DistributionTechRequire());
        // ...
        map.put(IsTechEnum.NON_TECH.getCode(), nonTechMap);


        // 通用（需求确认、评价）
        baseMap.put("confirm", new BaseConfirmRequire());
        baseMap.put("evaluate", new BaseEvaluateRequire());
    }

    /**
     * 获取具体的 Require 对象
     *
     * @param isTech 技术类型
     * @param type   操作类型
     * @return Require
     */
    public static Require getValue(String isTech, String type) {

        // 需求通用模块处理（确认、评价）
        switch (type) {
            case "confirm":
                return baseMap.get("confirm");
            case "evaluate":
                return baseMap.get("evaluate");
        }

        // 1、获取技术类型的map
        Map<String, Require> map = RequireMap.map.get(isTech);

        // 2、获取技术类型的map里面的具体map
        return Optional.ofNullable(map.get(type)).
                orElseThrow(() -> new RuntimeException(type + ":找不到对应处理类"));
    }

}
