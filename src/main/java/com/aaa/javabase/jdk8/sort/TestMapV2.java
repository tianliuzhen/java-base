package com.aaa.javabase.jdk8.sort;

import com.aaa.javabase.domain.Student;

import java.util.*;


/**
 * 如何对Map集合里的对象进行排序
 * 参考：https://blog.csdn.net/qq_43182556/article/details/84877213
 * @author liuzhen.tian
 * @version 1.0 TestMapV.java  2020/12/9 17:35
 */
public class TestMapV2 {
    public static void main(String[] args) {
        testMapV2();

    }
    private static void testMapV2() {
        Map<String, Student> budget = new HashMap<>();
        budget.put("a", new Student("tom", 23));
        budget.put("c", new Student("tom1", 13));
        budget.put("b", new Student("tom2", 33));

        sortMap(budget);

    }

    private static void sortMap(Map<String, Student> budget) {

        Set<Map.Entry<String, Student>> entrySet = budget.entrySet();

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
