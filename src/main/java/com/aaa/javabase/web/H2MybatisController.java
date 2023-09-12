package com.aaa.javabase.web;

import com.aaa.javabase.domain.CommonBeanModel;
import com.aaa.javabase.domain.Demo;
import com.aaa.javabase.h2.Model.Dept;
import com.aaa.javabase.h2.mapper.DeptMapper;
import com.aaa.javabase.h2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 H2Controller.java  2022/9/19 20:52
 */

@RestController
@RequestMapping(value = "/h2MybatisController")
public class H2MybatisController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Demo demo;

    @Autowired
    private DeptMapper deptMapper;


    @Autowired
    private CommonBeanModel commonBeanModel;

    @GetMapping(value = "/findAll")
    public void findAll() {
        for (int i = 0; i <2000; i++) {
            new Thread(() -> {
                try {
                    // 1MB=1024KB=1024x1024=1048576bytes
                    // Byte[] bytes = new Byte[1024 * 1024 * 1];
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        // for (int i = 0; i < 10; i++) {
        //     mapper.insert(new User(null, "name" + 1, 1, ""));
        // }
        // mapper.getUserMap(1L);
        // Dept one = deptMapper.getDeptById(1L);
        Dept one2 = deptMapper.getOne(1L, 20L);
        System.out.println();
    }
}
