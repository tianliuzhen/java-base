package com.aaa.javabase.h2;

import com.aaa.javabase.h2.Model.User;
import com.aaa.javabase.h2.Model.UserBill;
import com.aaa.javabase.h2.mapper.UserBillMapper;
import com.aaa.javabase.h2.mapper.UserMapper;
import com.aaa.javabase.util.StreamUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 UserMapperTest.java  2022/9/17 21:47
 */
@SpringBootTest
@ActiveProfiles("test") // 在类上 @ActiveProfiles(“dev”)，作用于整个类。
public class UserMapperTest {

    @Resource
    private UserMapper mapper;

    @Resource
    private UserBillMapper userBillMapper;

    @Test
    public void findAll() {
        for (int i = 0; i < 10; i++) {
            mapper.insert(new User(null, "name" + 1, 1, ""));
        }
        mapper.selectList(null).forEach(System.out::println);
    }


    /**
     * 嵌套查询 一对一 和 一对多测试
     * association 和 collection 标签测试
     */
    @Test
    public void getOneMap() {
        mapper.getUserMap(null);
        System.out.println();
    }

    /**
     * 嵌套查询-循环依赖测试
     */
    @Test
    public void loopUserBill() {
        List<UserBill> userBillByUserId = userBillMapper.getUserBillByUserId(1);
        System.out.println();
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    static private Configuration configuration;
    @PostConstruct
    public void init(){
        configuration = sqlSessionFactory.getConfiguration();
    }


    /**
     * 嵌套查询-循环依赖测试
     */
    @Test
    public void lazyUserBill() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Configuration configuration = sqlSession.getConfiguration();
        // 因为idea debug 默认会调用 toString方法，而 configuration 默认对【 "equals", "clone", "hashCode", "toString"】 这四个方法会直接触发懒加载
        // 所以这里设置禁用此懒加载触发方法
        configuration.setLazyLoadTriggerMethods(new HashSet<>());

        UserBillMapper mapper = sqlSession.getMapper(UserBillMapper.class);
        List<UserBill> userBillList = mapper.getUserBillByUserIdV2(1);
        for (UserBill userBill : userBillList) {
            byte[] bytes = StreamUtil.writeObject(userBill);
            UserBill userBillClass = StreamUtil.readObject(bytes, UserBill.class);
            // 需要设置全局一下 configuration-factory ，否则报错： Cannot get Configuration as configuration factory was not set.
            userBillClass.getUser();
            System.out.println();
        }
        sqlSession.close();
    }

    public static class ConfigurationFactory{
        // com.aaa.javabase.h2.UserMapperTest$ConfigurationFactory
        public static Configuration getConfiguration1(){
            return configuration;
        }
    }
}
