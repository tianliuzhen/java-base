package com.aaa.javabase.jdk8.stream.groupby;

import com.aaa.javabase.domain.Device;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.internal.Maps;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 参考：https://blog.csdn.net/qq_42701294/article/details/107291917
 *
 * @author liuzhen.tian
 * @version 1.0 TestDevice.java  2021/7/18 9:38
 */
public class TestDevice {
    public static void main(String[] args) {
        List<Device> deviceList = build();
        /**
         * 需求：求出下面的json
         *    1幢：
         *      1单元：
         *          :101
         *          :102
         *      2单元
         *          :201
         */
        handle(deviceList);

    }

    private static void handle(List<Device> deviceList) {
        // 最终返回结果
        Map<String, Map<String, List<Device>>> res = new HashMap<>();

        // 1、获取第一层 key：幢
        Map<String, List<Device>> collect = deviceList.stream()
                .collect(Collectors.groupingBy(Device::getBuilding));
        collect.forEach((key, val) -> {
            System.out.println("幢：" + key + " 单元：" + val);
            Map<String, List<Device>> keyMap = new HashMap<>();
            res.put(key, keyMap);
            val.forEach(e -> {
                keyMap.put(e.getUnit(), new ArrayList<>());
            });
        });

        // 2、获取第二层 key：单元
        Map<String, List<Device>> collect1 = deviceList.stream().collect(Collectors.groupingBy(e -> e.getBuilding() + "#" + e.getUnit()));
        collect1.forEach((k, v) -> {
            String[] key = StringUtils.split(k, "#");
            String building = key[0];
            String unit = key[1];
            List<Device> list = res.get(building).get(unit);
            list.addAll(v);
        });


        System.out.println(JSON.toJSONString(res));
        System.out.println("======================");

        // 3、转List
        List<RespDevice> resList = res.entrySet().stream().map(e ->
                new RespDevice(
                        e.getKey(),
                        e.getValue().entrySet().stream().map(k -> new RespDeviceUit(k.getKey(), k.getValue())).collect(Collectors.toList())))
                .collect(Collectors.toList());

        System.out.println(JSON.toJSONString(resList));
    }


    private static List<Device> build() {
        List<Device> list = Lists.newArrayList();
        list.add(new Device("1幢", "1单元", "101", "设备1"));
        list.add(new Device("1幢", "1单元", "102", "设备2"));
        list.add(new Device("1幢", "2单元", "201", "设备3"));
        list.add(new Device("1幢", "3单元", "301", "设备4"));
        list.add(new Device("2幢", "1单元", "101", "设备5"));
        list.add(new Device("2幢", "2单元", "201", "设备6"));
        return list;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RespDevice {
        private String building;
        private List<RespDeviceUit> unit;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RespDeviceUit {
        private String unit;
        private List<Device> deviceList;
    }
}
