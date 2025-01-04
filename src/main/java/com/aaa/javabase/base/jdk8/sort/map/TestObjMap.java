package com.aaa.javabase.base.jdk8.sort.map;

import com.aaa.javabase.domain.Student;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 如何对Map集合里的对象进行排序
 * 参考：https://blog.csdn.net/qq_43182556/article/details/84877213
 * @author liuzhen.tian
 * @version 1.0 TestMapV.java  2020/12/9 17:35
 */
public class TestObjMap {
    public static void main(String[] args) {
        testMapV2();

    }
    private static void testMapV2() {
        Map<String, Student> map = new HashMap<>();
        map.put("a", new Student("tom", 23));
        map.put("c", new Student("tom1", 13));
        map.put("b", new Student("tom2", 33));

        System.out.println("-------采用list");
        sortMap(map);

        System.out.println("-------采用stream");
        sortMapV2(map);
    }

    private static void sortMapV2(Map<String, Student> map) {

        //stream原生写法，默认是升序，降序的话（调换o1.getAge().compareTo(o2.getAge()) 即可）
        Map<String, Student> collect = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        })).collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue,
                (oldVal, newVal) -> oldVal,
                LinkedHashMap::new
        ));
        System.out.println(collect);

        /**
         *  使用 lombok 简化写法，这里省略一部分代码
         */

        // 写法2（升序）
        map.entrySet().stream().sorted(Map.Entry.comparingByValue((o1, o2) -> o1.getAge().compareTo(o2.getAge())));
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Student::getAge)));


        // 写法3 （降序）
        map.entrySet().stream().sorted(
                Map.Entry.<String, Student>comparingByValue(Comparator.comparing(Student::getAge)).reversed());
        map.entrySet().stream().sorted(Map.Entry.comparingByValue((o1, o2) -> o2.getAge().compareTo(o1.getAge())));
    }

    private static void sortMap(Map<String, Student> map) {

        Set<Map.Entry<String, Student>> entrySet = map.entrySet();

        List<Map.Entry<String, Student>> list = new ArrayList(entrySet);

        //  使用lombok 简化
      /**
       * // 升序
       *   Collections.sort(list, Comparator.comparingInt(o -> o.getValue().getAge()));
       *   或者    Collections.sort(list, (o1, o2) -> o1.getValue().getAge()- o2.getValue().getAge());
       * // 降序
       *   Collections.sort(list, (o1, o2) -> o2.getValue().getAge()- o1.getValue().getAge());
       *
       */
        Collections.sort(list, new Comparator<Map.Entry<String, Student>>() {
            @Override
            public int compare(Map.Entry<String, Student> o1, Map.Entry<String, Student> o2) {
                return o1.getValue().getAge()-o2.getValue().getAge();
            }
        });

        LinkedHashMap<String, Student> link = new LinkedHashMap<String, Student>();

        System.out.println("-----------------------------");
        for (Map.Entry<String, Student> entry : list) {
            link.put(entry.getKey(), entry.getValue());
        }
        System.out.println(link);

    }
}
