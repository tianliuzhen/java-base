package com.aaa.javabase.mybatis;

import lombok.SneakyThrows;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.InputStream;

/**
 * @author liuzhen.tian
 * @version 1.0 SqlSessionUtil.java  2023/4/8 11:18
 */
public class SqlSessionUtil {

    @SneakyThrows
    public static SqlSessionFactory getSqlSessionFactory() {
        String resource = "mybatis/mybatis-config.xml";
        // 这里路径寻址最终走的是，java.lang.ClassLoader.getResourceAsStream
        // 如果不是/开头，默认找的是classPath下的路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }


    @SneakyThrows
    public static SqlSessionFactory getSqlSessionFactoryV2() {
        Configuration configuration = new Configuration();
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setUrl("jdbc:h2:mem:test");
        pooledDataSource.setDriver("org.h2.Driver");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("123456");
        JdbcTransactionFactory jdbcTransaction = new JdbcTransactionFactory();
        Environment environment = new Environment("dev", jdbcTransaction, pooledDataSource);
        configuration.setEnvironment(environment);
        // 注意不支持多个包，只能一个包
        configuration.addMappers("com.aaa.javabase.h2");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
