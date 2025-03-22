package com.aaa.javabase.mybatis;

import com.aaa.javabase.h2.Model.User;
import com.aaa.javabase.h2.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMybatisSqlSession.java  2023/4/8 8:59
 */

@SpringBootTest // 因为本地集成的h2数据库的内存模式，基于同一个jvm才能连接到，所以这里要启动一下整个项目
public class TestMybatisSqlSession {

    @Test
    public void select() throws IOException {
        SqlSessionFactory sqlSessionFactory = SqlSessionUtil.getSqlSessionFactoryV2();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(1L);

        sqlSession.close();
    }

    @Test
    public void update() throws IOException {
        SqlSessionFactory sqlSessionFactory = SqlSessionUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.insert(new User(26));

        // 如果不设置 openSession(true);需要收到commit
        sqlSession.commit();
        sqlSession.close();
    }

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = SqlSessionUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.insert(new User(26));

        // 如果不设置 openSession(true);需要收到commit
        sqlSession.commit();
        sqlSession.close();
    }



}
