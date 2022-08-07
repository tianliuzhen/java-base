package com.aaa.javabase.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://blog.csdn.net/ZYC88888/article/details/102548592\
 * <p>
 * 引用是通过"$ref"来表示
 * 引用	                 |   描述
 * "$ref":".."	         |   上一级
 * "$ref":"@"	         |   当前对象，也就是自引用
 * "$ref":"$"	         |   根对象
 * "$ref":"$.children.0" |   基于路径的引用，相当于 root.getChildren().get(0)
 * {"$ref":"../.."}	     |   引用父对象的父对象
 *
 * @author liuzhen.tian
 * @version 1.0 TestCircularReference.java  2022/8/7 21:36
 */
public class TestCircularAndRepeatReference {
    public static void main(String[] args) {
        /**
         * 重复引用
         * 指一个对象重复出现多次
         */
        testRepeat();

        /**
         * 循环引用
         * 指你心里有我，我心里有你(互相引用)，这个问题比较严重，如果处理不好就会出现StackOverflowError异常
         */
        testCircular();

        /**
         * 如何关闭引用检测后：
         *      FastJson提供了SerializerFeature.DisableCircularReferenceDetect这个序列化选项，用来关闭引用检测。
         *      关闭引用检测后，重复引用对象时就不会被$ref代替，但是在循环引用时也会导致StackOverflowError异常。
         *
         * 说明：
         *      避免重复引用序列化时显示$ref
         *
         *      在编码时，使用新对象为集合或对象赋值，而非使用同一对象
         *      不要在多处引用同一个对象，这可以说是一种java编码规范，需要时刻注意。
         *      不要关闭FastJson的引用检测来避免显示$ref
         *      引用检测是FastJson提供的一种避免运行时异常的优良机制，
         *      如果为了避免在重复引用时显示$ref而关闭它，会有很大可能导致循环引用时发生StackOverflowError异常。
         *      这也是FastJson默认开启引用检测的原因。
         *
         * 循环引用的解决方法：
         *      1.如果你前端用不到这个属性在该属性的get方法上加上注解@JSONField(serialize=false),
         *       这样该属性就不会被序列化出来，这个也可以解决重复引用
         *      2.修改表结构，出现循环引用了就是一个很失败的结构了，不然准备迎接StackOverflowError异常。
         */
    }


    public static void testRepeat() {
        List<Object> list = new ArrayList<>();
        Object obj = new Object();
        list.add(obj);
        list.add(obj);
        // 如果检测到存在重复/循环引用的情况，fastjson默认会以“引用标识”代替同一对象，而非继续循环解析导致StackOverflowError
        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list));
        // 输出结果：
        /**
         * [
         *     {},  //obj的实体
         *     {
         *         "$ref": "$[0]"   //对obj的重复引用的处理
         *     }
         * ]
         */

        // 重复引用的解决方法
        // 1.单个关闭
        JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
        // 2.全局配置关闭
        // JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();


    }

    public static void testCircular() {
        // 循环引用的特殊情况，自引用
        Map<String, Object> map = new HashMap<>();
        map.put("map", map);

        // map1引用了map2，而map2又引用map1，导致循环引用
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("map", map2);
        map2.put("map", map1);

        // 如果检测到存在重复/循环引用的情况，fastjson默认会以“引用标识”代替同一对象，而非继续循环解析导致StackOverflowError
        System.out.println("JSON.toJSONString(map1) = " + JSON.toJSONString(map1));

        // 输出结果如下：
        /**
         *    {
         *     "map": {
         *          // map2的key:value对
         *         "map": {
         *              // 指向map1，对循环引用的处理
         *             "$ref": ".."
         *         }
         *     }
         * }
         */
    }
}
