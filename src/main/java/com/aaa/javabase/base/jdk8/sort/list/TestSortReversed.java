package com.aaa.javabase.base.jdk8.sort.list;

import com.aaa.javabase.domain.Student;
import org.assertj.core.util.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestSortReversed.java  2025/6/26 21:46
 */
public class TestSortReversed {
    public static void main(String[] args) {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("tom", 23));
        list.add(new Student("tom1", 13));
        list.add(new Student("tom2", 33));

        List<Student> collect = list.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList());

        /*
         * 编译器无法推断 e 的类型，因为在 lambda 表达式 e -> e.getAge() 中，e 的类型没有被明确指定。当你在方法引用 Student::getAge 中时，编译器知道 e 是 Student 类型，但在 lambda 表达式中，类型信息丢失了。
         */
        // List<Student> collect2 = list.stream().sorted(Comparator.comparing(e -> e.getAge()).reversed()).collect(Collectors.toList());

        /**
         * 为什么 Comparator.comparing(e -> e.getAge()) 可以编译，但加上 .reversed() 就不行？
         原因：
         .reversed() 返回的是 Comparator<T>，但此时 T 的类型信息已经丢失（由于 泛型类型擦除 和 lambda 表达式的类型推断限制）。
         Comparator.comparing(e -> e.getAge()) 本身可以推断 T，但一旦 .reversed() 被调用，编译器无法再回溯 T 的类型，导致 e 的类型无法确定。
         */
        List<Student> collect3 = list.stream().sorted(Comparator.comparing(e -> e.getAge())).collect(Collectors.toList());

        // 显式指定类型参数
        List<Student> collect4 = list.stream().sorted(Comparator.<Student, Integer>comparing(e -> e.getAge()).reversed()).collect(Collectors.toList());
    }
}
