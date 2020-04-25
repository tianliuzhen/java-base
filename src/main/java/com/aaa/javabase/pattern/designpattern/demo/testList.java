package com.aaa.javabase.pattern.designpattern.demo;

import java.util.*;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/16
 */
public class testList {
    public static void main(String[] args) {
        int arr[]={1,2,3,1,2,4,6,87,4,3,8,9,3,2,46,7};
        Map<Integer, Integer> map=new HashMap<Integer, Integer>();
        for (int i=0;i<arr.length;i++){
            if(map.get(arr[i])!=null){
                map.put(arr[i], map.get(arr[i])+1);
            }else{
                map.put(arr[i], 1);
            }

        }
        //得到map中所有的键
        Set<Integer> keyset=map.keySet();
        //创建set集合的迭代器
        Iterator<Integer> it=keyset.iterator();

        while (it.hasNext()) {
            Integer key=it.next();
            Integer value=map.get(key);
            System.out.print(key+"共有"+value+"次    ");
        }
    }

}
