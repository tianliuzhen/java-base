package com.aaa.javabase.h2;

import com.aaa.javabase.h2.Model.UserExtModel;
import com.aaa.javabase.h2.mapper.UserExtMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 UserExtMapperTest.java  2023/2/16 20:59
 */
@SpringBootTest
@ActiveProfiles("test") // 在类上 @ActiveProfiles(“dev”)，作用于整个类。
public class UserExtMapperTest {
    @Resource
    private UserExtMapper userExtMapper;


    @Test
    public void getUserExtList() {
        List<UserExtModel> userExtList = userExtMapper.getUserExtList();
        System.out.println();
    }
}
