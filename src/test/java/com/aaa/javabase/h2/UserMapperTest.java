package com.aaa.javabase.h2;

import com.aaa.javabase.h2.Model.User;
import com.aaa.javabase.h2.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuzhen.tian
 * @version 1.0 UserMapperTest.java  2022/9/17 21:47
 */
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void findAll() {
        for (int i = 0; i < 10; i++) {
            mapper.insert(new User(null, "name" + 1, 1, ""));
        }


        mapper.selectList(null).forEach(System.out::println);
    }
}
