package com.aaa.javabase.mybatis;

import com.aaa.javabase.h2.Model.User;
import com.aaa.javabase.h2.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * 一级缓存测试
 *
 * @author liuzhen.tian
 * @version 1.0 TestFirstCatch.java  2022/9/25 19:27
 */

@SpringBootTest
public class TestFirstCache {

    /**
     * 直接注入的 SqlSession 是 org.mybatis.spring.SqlSessionTemplate.SqlSessionInterceptor 动态代理执行的
     * ** 每次调用都会重写 openSession，所以每次查询同个接口同个sql，是不走一级缓存的
     * ** 加上事务之后，就可以共享 SqlSession了。
     */
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    @Transactional
    public void test31() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.getUserByIdOrName(1L,"xzxx");
    }


    /**
     * spring 托管 sqlSession
     * 加上事务之后，就可以共享 SqlSession了。
     */
    @Test
    @Transactional
    public void test3() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.selectList(null);
        mapper.selectList(null);
    }

    /**
     * spring 托管 sqlSession
     * 不加事务，每次查询都会打开一个新的sqlSession
     */
    @Test
    public void test2() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.selectList(null);
        mapper.selectList(null);
    }

    /**
     * mybatis 托管 sqlSession 底层是 DefaultSqlSession
     */
    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 默认是开启一级缓存的
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.insert(new User(100L, "", 100, "7460@qq.com"));

        // 查询俩次一样接口的sql，实际上查询一次
        mapper.selectById(1L);
        mapper.selectById(1L);
    }

    /**
     * 二级缓存测试
     * mybatis 托管 sqlSession 底层是 DefaultSqlSession
     */
    @Test
    public void test4() {
        // sqlSession1 自动提交没有用，必须设置手动提交
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        mapper1.selectById(1L);
        sqlSession1.commit();

        // sqlSession2 设置手动提交
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        mapper2.selectById(1L);
        sqlSession2.commit();
    }

}
