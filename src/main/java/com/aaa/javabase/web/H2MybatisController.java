package com.aaa.javabase.web;

import com.aaa.javabase.domain.Demo;
import com.aaa.javabase.h2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhen.tian
 * @version 1.0 H2Controller.java  2022/9/19 20:52
 */

@RestController
@RequestMapping(value = "/h2MybatisController")
public class H2MybatisController {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private Demo demo;


    @GetMapping(value = "/findAll")
    public void findAll() {

        // for (int i = 0; i < 10; i++) {
        //     mapper.insert(new User(null, "name" + 1, 1, ""));
        // }
        mapper.selectList(null);

        mapper.selectList(null);


    }
}
