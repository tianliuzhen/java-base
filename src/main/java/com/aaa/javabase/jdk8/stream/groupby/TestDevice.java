package com.aaa.javabase.jdk8.stream.groupby;

import com.aaa.javabase.domain.Device;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 参考：https://blog.csdn.net/qq_42701294/article/details/107291917
 *
 * @author liuzhen.tian
 * @version 1.0 TestDevice.java  2021/7/18 9:38
 */
public class TestDevice {
    public static void main(String[] args) {
        // 1、mock数据
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
        // 2、执行
        handle(deviceList);

    }

    private static void handle(List<Device> deviceList) {
        // 最终返回结果
        Map<String, Map<String, List<Device>>> res = new TreeMap<>();

        // 1、获取第一层 key：单元
        Map<String, List<Device>> collect = deviceList.stream()
                .collect(Collectors.groupingBy(Device::getUnit));
        collect.forEach((key, val) -> {
            Map<String, List<Device>> keyMap = new TreeMap<>();
            res.put(key, keyMap);
            val.forEach(e -> {
                keyMap.put(e.getFloorNo(), new ArrayList<>());
            });
        });

        // 2、获取第二层 key：楼层
        Map<String, List<Device>> collect1 = deviceList.stream().collect(Collectors.groupingBy(e -> e.getUnit() + "#" + e.getFloorNo()));
        collect1.forEach((k, v) -> {
            String[] key = StringUtils.split(k, "#");
            String unit = key[0];
            String FloorNo = key[1];
            List<Device> list = res.get(unit).get(FloorNo);
            list.addAll(v);
        });


        System.out.println("======================");

        // 3、转List
        List<DeviceTreeMenu> resList = res.entrySet().stream().map(e ->
                new DeviceTreeMenu(
                        e.getKey(),
                        e.getKey() + "单元",
                        e.getValue()
                                .entrySet()
                                .stream()
                                .map(k -> new DeviceTreeMenu(k.getKey(), k.getKey() + "楼", getDeviceTreeMenu(k.getValue())))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        System.out.println(JSON.toJSONString(resList));
    }

    public static List<DeviceTreeMenu> getDeviceTreeMenu(List<Device> device) {
        List<DeviceTreeMenu> list = Lists.newArrayList();
        for (Device device1 : device) {
            list.add(new DeviceTreeMenu(device1.getDevSn(), device1.getDevName()));
        }
        return list;
    }


    private static List<Device> build() {
        List<Device> list = Lists.newArrayList();
        list.add(new Device("1", "1", "101", "1", "设备1"));
        list.add(new Device("1", "1", "102", "2", "设备2"));
        list.add(new Device("1", "2", "201", "3", "设备3"));
        list.add(new Device("1", "3", "301", "4", "设备4"));
        list.add(new Device("2", "1", "101", "5", "设备5"));
        list.add(new Device("2", "2", "201", "6", "设备6"));
        return list;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceTreeMenu {
        private Integer id;
        private String field;
        private String title;
        private boolean checked;
        private List<DeviceTreeMenu> children;


        public DeviceTreeMenu(String field, String title, List<DeviceTreeMenu> children) {
            this.field = field;
            this.title = title;
            this.children = children;
        }

        public DeviceTreeMenu(String field, String title) {
            this.field = field;
            this.title = title;
        }
    }

}
