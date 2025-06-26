package com.aaa.javabase.web;

import com.aaa.javabase.domain.CommonBeanModel;
import com.aaa.javabase.domain.Demo;
import com.aaa.javabase.h2.Model.Dept;
import com.aaa.javabase.h2.Model.UserBill;
import com.aaa.javabase.h2.mapper.DeptMapper;
import com.aaa.javabase.h2.mapper.UserMapper;
import com.aaa.javabase.h2.tkmapper.TkDeptMapper;
import com.aaa.javabase.spring.aopBeanTest.MyCglibBeanService;
import com.aaa.javabase.spring.aopBeanTest.MyJavaBeanService;
import com.aaa.javabase.spring.injection.construction.Abean;
import com.aaa.javabase.spring.injection.construction.Bbean;
import com.aaa.javabase.spring.injection.setter.Cbean;
import com.aaa.javabase.util.jdbc.H2JdbcExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private Abean abean;
    @Autowired
    private Bbean bbean;
    @Autowired
    private Cbean cbean;
    @Autowired
    private MyCglibBeanService myCglibBeanService;
    @Autowired
    private MyJavaBeanService myJavaBeanService;

    @GetMapping(value = "/h2JdbcExample")
    public void h2JdbcExample() {
        H2JdbcExample.H2Jdbc();
    }


    @GetMapping(value = "/selectByDept")
    public void selectByDept() {
        Dept dept = new Dept();
        dept.setDeptNo(1);
        dept.setDeptName("研发");
        List<Dept> depts = deptMapper.selectByDept(dept);
    }

    @Autowired
    private TkDeptMapper tkDeptMapper;

    @GetMapping(value = "/tkDeptMapperTest")
    public void tkDeptMapperTest() {
        Example.builder(UserBill.class).build()
                .createCriteria().andEqualTo("aaa","123");
        tkDeptMapper.selectByExample(null);

        Dept deptCheck = tkDeptMapper.getDeptCheck("12");
    }


    @GetMapping(value = "/getDeptById")
    public void getDeptById() {
       deptMapper.getDeptById(1L);
    }

    @GetMapping(value = "/findAll")
    public void findAll() {

        // for (int i = 0; i < 10; i++) {
        //     mapper.insert(new User(null, "name" + 1, 1, ""));
        // }
        // mapper.getUserMap(1L);
        // Dept one = deptMapper.getDeptById(1L);
        Dept one2 = deptMapper.getOne(1L, 20L);
        System.out.println();
    }

    @GetMapping("/getIp3")
    public String getIp3(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return "Client IP address: " + ipAddress.split(",")[0];
    }


}
