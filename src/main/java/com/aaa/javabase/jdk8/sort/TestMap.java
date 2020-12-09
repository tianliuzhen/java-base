package com.aaa.javabase.jdk8.sort;

import com.aaa.javabase.util.CloneUtil;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author liuzhen.tian
 * @version 1.0 TestMap.java  2020/12/9 17:12
 */
public class TestMap {

    public static void main(String[] args) {
        /**
         * 如果针对 嵌套的map 进行排序呢，其实还是比较复杂的。
         * 1、需要用到 深拷贝
         * 2、需要用到单个 stream 对 value 的排序
         * 3、构造临时变量 （记录排序的key）
         *
         *
         */
    }

    /**
     * 这里是 嵌套map的排序，主要排序的是 Map<String, DashboardSituationVo>
     */
    private Map<String, Map<String, Map<String, DashboardSituationVo>>> sortByField(Map<String, Map<String, Map<String, DashboardSituationVo>>> dashboardSituationDetailVoV2, String sortField, String sortType) {
        /**
         * 预估实际成交额	  近7天总成交额（元）	近7天平均成交额（元）	总订单数	 总UV	近七天总UV	平均AOV	平均订单转化率（%）
         * 1.
         */
        dashboardSituationDetailVoV2.values().forEach(all ->{
            //根据 DashboardSituationVo 里面的字段排序
            Map<String, Map<String, DashboardSituationVo>> allMap = all;

            allMap.forEach((k,v)->{
                Map<String, DashboardSituationVo> oneMap = v;
                // 1. 深拷贝一个map
                Map<String, DashboardSituationVo> deepMap = CloneUtil.deepCloneMap(oneMap);
                // 2. 这里的构造一个临时排序变量 LinkedHashMap
                Map<String, BigDecimal> linkedMap = new LinkedHashMap();
                if(sortField.equals("perdayTurnover")){
                    Map<String, BigDecimal> finalLinkedMap = linkedMap;
                    oneMap.forEach((k1, v1)-> finalLinkedMap.put(k1, v1.getPerdayTurnover()));
                    if ("desc".equals(sortType)) {
                        linkedMap = sortByValue(linkedMap, 0);
                    }
                    if ("asc".equals(sortType)) {
                        linkedMap = sortByValue(linkedMap, 1);
                    }
                }
                Map<String, DashboardSituationVo>  needLinkedMap= new LinkedHashMap();
                // 3. 构造排序后需要的 linkedMap
                linkedMap.keySet().forEach(key ->{
                    //此时的 linkedMap 是有序的
                    needLinkedMap.put(key,deepMap.get(key));
                });
                // 4. 进行排序，清空原有数据，从深拷贝中重新赋值 map
                oneMap.clear();
                allMap.put(k,needLinkedMap);

            });

        });

        return dashboardSituationDetailVoV2;
    }




    /**
     * 对 map 的 value 进行排序
     * @param map
     * @param sortType
     * @return java.util.Map<K,V>
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, int sortType) {
        Map<K, V> result = new LinkedHashMap<>();
        if (sortType == 0) {
            map.entrySet().stream()
                    .sorted(Map.Entry.<K, V>comparingByValue()
                            .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }

        if (sortType == 1) {
            map.entrySet().stream()
                    .sorted(Map.Entry.<K, V>comparingByValue()
                    ).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }

        return result;
    }

}
