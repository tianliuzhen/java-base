package com.aaa.javabase.pattern.structure.proxy.demo3;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * 动态代理具体步骤：
 *
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/9/25 13:57
 */
public class TestMain {
    public static void main(String[] args) {
        SqlSession sqlSession = (SqlSession) newProxyInstance(
                SqlSessionFactory.class.getClassLoader(),
                new Class[]{SqlSession.class},
                new SqlSessionInterceptor());

        System.out.println();
    }
}
