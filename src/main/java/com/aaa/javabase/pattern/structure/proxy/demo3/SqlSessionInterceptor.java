package com.aaa.javabase.pattern.structure.proxy.demo3;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liuzhen.tian
 * @version 1.0 SqlSessionInterceptor.java  2022/9/25 16:48
 */
public class SqlSessionInterceptor implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // SqlSession sqlSession = SqlSessionManager.newInstance((InputStream) null);
        // SqlSession sqlSession = new DefaultSqlSession(null,null);
        SqlSession sqlSession = new MySqlSession();
        System.out.println("1111111111111111");
        Object result = method.invoke(sqlSession, args);
        return result;
    }
}
