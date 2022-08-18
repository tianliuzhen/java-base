package com.aaa.javabase.spring;

import com.aaa.javabase.domain.Grade;
import com.aaa.javabase.domain.Student;
import org.assertj.core.util.Lists;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 参考：https://blog.51cto.com/u_3631118/3121519?b=totalstatistic
 *
 * @author liuzhen.tian
 * @version 1.0 TestSpEL.java  2022/8/18 20:38
 */
public class TestSpEL {
    public static void main(String[] args) {
        // Test1();


        Test2();

        // Test3();
    }

    private static void Test3() {
        //创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();

        //设置解析上下文(有哪些占位符，以及每种占位符的值)
        StandardEvaluationContext context = new StandardEvaluationContext();

        // mock 设置一个对象
        Grade grade = new Grade();
        grade.setClassId(1L);
        grade.setClassName("1班");
        grade.setStudent(Lists.newArrayList(new Student("tom")));

        // TODO: setRootObject()进去的取值时，不是必须指定前缀的 #
        context.setRootObject(grade);

        //获取表达式，获取替换后的结果：此时 #root = grade
        Object value = parser.parseExpression("grade").getValue(context);
        // 取班级,且不区分大小写：classId、ClassId
        Object classId = parser.parseExpression("classId").getValue(context);
        System.out.println();
    }

    public static void Test2() {
        //创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();

        //设置解析上下文(有哪些占位符，以及每种占位符的值)
        EvaluationContext context = new StandardEvaluationContext();

        // mock 设置一个对象
        Grade grade = new Grade();
        grade.setClassId(1L);
        grade.setClassName("1班");
        grade.setStudent(Lists.newArrayList(new Student("tom")));

        // TODO: setVariable()进去的取值时，是必须指定前缀的 #
        context.setVariable("grade", grade);

        //获取表达式，获取替换后的结果
        Object value = parser.parseExpression("#grade").getValue(context);
        // 取班级
        Object classId = parser.parseExpression("#grade.classId").getValue(context);
        System.out.println();
    }

    public static void Test1() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        System.out.println(message);

        // 调用'getBytes()'
        Expression exp2 = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) exp2.getValue();

        // SpEL同时也支持级联属性调用、和标准的prop1.prop2.prop3方式是一样的；同样属性值设置也是类似的方式.

        // 调用 'getBytes().length'
        Expression exp3 = parser.parseExpression("'Hello World'.bytes.length");
        int length = (Integer) exp3.getValue();
    }
}
