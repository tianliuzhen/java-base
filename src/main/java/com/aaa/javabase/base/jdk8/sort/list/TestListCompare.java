package com.aaa.javabase.base.jdk8.sort.list;

import com.aaa.javabase.domain.Student;
import org.assertj.core.util.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 参考：https://www.cnblogs.com/skywang12345/p/3324788.html
 * @author liuzhen.tian
 * @version 1.0 TestCompare.java  2020/12/10 10:50
 */
public class TestListCompare {
    public static void main(String[] args) {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("tom", 23));
        list.add(new Student("tom1", 13));
        list.add(new Student("tom2", 33));

        System.out.println("------输出原始的list");
        System.out.println(list);

        System.out.println("------通过 Person实现的Comparable<String>接口”进行排序，即会根据“age”进行排序");
        Collections.sort(list);
        System.out.println(list);

        System.out.println("------通过 Collections.reverseOrder()，对Person实现的Comparable 降序 ");
        Collections.sort(list,Collections.reverseOrder());
        System.out.println(list);

        System.out.println("-----------上面是Comparable下面是 Comparator ------------");

        System.out.println("-----通过“比较器(AscAgeComparator)”，对list进行升序");
        Collections.sort(list,new AscAgeComparator());
        System.out.println(list);

        System.out.println("-----通过“比较器(DescAgeComparator)”，对list进行降序");
        Collections.sort(list,new DescAgeComparator());
        System.out.println(list);


    }

    /**
     * 构造 年龄升序 Comparator
     */
    public static class AscAgeComparator implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.getAge() - o2.getAge();
        }
    }

    /**
     * 构造 年龄降序 Comparator
     */
    public static class DescAgeComparator implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o2.getAge() - o1.getAge();
        }
    }
}
