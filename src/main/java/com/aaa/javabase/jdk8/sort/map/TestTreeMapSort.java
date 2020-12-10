package com.aaa.javabase.jdk8.sort.map;

import java.util.*;

/**
 * @author liuzhen.tian
 * @version 1.0 TestTreeMap.java  2020/12/10 14:58
 */
public class TestTreeMapSort {
    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<String, String>();

        map.put("a", "123");
        map.put("c", "456");
        map.put("d", "234");
        map.put("b", "890");

        System.out.println("-------排序前");
        System.out.println(map);

        // 默认为升序，降序的话，调换 o1 , 02 位置即可

        //按Key进行排序
        sortByKey(map);

        //按Value进行排序
        sortByValues(map);

    }

    private static void sortByKey(Map<String, String> map) {
        Map<String, String> resultMap = sortMapByKey(map);
        System.out.println("-------key 排序 ");
        System.out.println(resultMap);
    }

    private static void sortByValues(Map<String, String> map) {
        Map<String, String> resultMap = sortMapByValue(map);
        System.out.println("-------value 排序 ");
        System.out.println(resultMap);
    }

    /**
     * 使用 Map按value进行排序
     * @param oriMap
     * @return
     */
    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String, String>> entryList = new ArrayList<>(oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    static class  MapValueComparator implements Comparator<Map.Entry<String, String>> {

        @Override
        public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
            // 默认为升序，降序的话，调换 o1 , 02 位置即可
            return o1.getValue().compareTo(o2.getValue());
        }
    }
    static class  MapKeyComparator implements Comparator<String>{

        @Override
        public int compare(String str1, String str2) {
            // 默认为升序，降序的话，调换 o1 , 02 位置即可
            return str2.compareTo(str1);
        }
    }


}


