package com.aaa.javabase.spring.autowriedAndResource;

import com.aaa.javabase.spring.autowriedAndResource.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/6/16
 */

@SpringBootTest
public class HelloTest {
    @Resource
    // @Autowired
    private HelloService helloV2ServiceImpl;
    @Test
    public void hello() {
        helloV2ServiceImpl.helloWord();
    }
}
