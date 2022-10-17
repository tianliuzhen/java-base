package com.aaa.javabase.mybatis;

import com.aaa.javabase.h2.Model.User;
import com.aaa.javabase.h2.mapper.UserMapper;
import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestExecutor.java  2022/10/6 18:38
 */
@SpringBootTest
public class TestExecutor {

    @Autowired
    private SqlSession sqlSession;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private Configuration configuration;
    private JdbcTransaction jdbcTransaction;

    @PostConstruct
    public void init() throws SQLException {
        configuration = sqlSession.getConfiguration();
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "root", "123456");
        jdbcTransaction = new JdbcTransaction(connection);
    }


    @Test
    public void simpleTest() throws SQLException {
        SimpleExecutor executor = new SimpleExecutor(configuration, jdbcTransaction);
        MappedStatement mappedStatement = configuration.getMappedStatement("com.aaa.javabase.h2.mapper.UserMapper.selectById");
        List<Object> list = executor.doQuery(
                mappedStatement,
                2L, RowBounds.DEFAULT,
                SimpleExecutor.NO_RESULT_HANDLER,
                mappedStatement.getBoundSql(2L));

        executor.doQuery(mappedStatement, 2L, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, mappedStatement.getBoundSql(2L));
        System.out.println(list);
    }

    @Test
    public void ReuseTest() throws SQLException {
        ReuseExecutor executor = new ReuseExecutor(configuration, jdbcTransaction);
        MappedStatement mappedStatement = configuration.getMappedStatement("com.aaa.javabase.h2.mapper.UserMapper.selectById");
        List<Object> list = executor.doQuery(
                mappedStatement,
                2L, RowBounds.DEFAULT,
                SimpleExecutor.NO_RESULT_HANDLER,
                mappedStatement.getBoundSql(2L));

        executor.doQuery(mappedStatement, 2L, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, mappedStatement.getBoundSql(2L));
        System.out.println(list);
    }


    /**
     * 下面是一个使用Mybatis的BatchExecutor进行批量操作的示例。在示例中我们创建了一个使用BatchExecutor的SqlSession，
     * 也可以是SqlSessionTemplate，其实现了SqlSession接口，底层通过一个SqlSession代理进行相应的操作。
     * 然后在循环中一次调用对应Mapper的新增操作，相当于调用BatchExecutor的doUpdate()，
     * 最后调用SqlSession的commit()方法提交事务，在SqlSession的commit()中会调用Executor的commit()，
     * 从而导致了executeBatch()的发生。
     */
    @Test
    public void BatchTest() {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserMapper mapper = session.getMapper(UserMapper.class);
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("Name_" + i);
            user.setEmail("email");
            mapper.insert(user);

            if (i == 9) {
                int a = 1 / 0;
            }
        }

        // 批量插入User
        session.commit();// 提交事务时批量操作才会写入数据库
        session.close();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            System.out.println(i);
        }
    }
}
