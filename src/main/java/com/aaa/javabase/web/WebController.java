package com.aaa.javabase.web;

import com.aaa.javabase.config.TestProperties;
import com.aaa.javabase.domain.BaseMain;
import com.aaa.javabase.service.GoodsService;
import com.aaa.javabase.spring.conditionBean.service.People;
import com.aaa.javabase.spring.injection.construction.Abean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version $ Id: WebTest.java, v 0.1 2020/6/18 11:02 liuzhen.tian Exp $
 */
@RestController
public class WebController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private Abean abean;


    /**
     * Map 接受 application.properties  map字段
     */
    @Value("#{${wei_xin_config}}")
    private Map<String, String> weiXinConfig;

    @GetMapping(value = "weiXinConfig")
    public Object weiXinConfig() {
        return weiXinConfig;
    }


    /**
     * 用户列表
     *
     * @param listForm
     */
    @RequestMapping(path = "list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(@RequestParam(required = true) String listForm) {
        return "str";
    }


    @GetMapping(value = "/getGoodsServiceGoodsNames")
    public void getGoodsServiceGoodsNames() {
        System.out.println(goodsService.getGoodsName());
    }

    /**
     * 测试返回两种时间格式
     * Date
     * DateTime
     *
     * @return
     */
    @PostMapping("/objectResponseDate")
    public Object response() {
        BaseMain baseMain = new BaseMain();
        baseMain.setDate(new Date());
        baseMain.setLocalDateTime(LocalDateTime.now());

        return baseMain;
    }


    @GetMapping("/testInt")
    public People testInt() {
        People people = new People();
        return people;

    }

    @GetMapping("/testBean")
    public void testBean() {
        abean.get();
    }

    @Autowired
    private TestProperties testProperties;

    @GetMapping("/testProperties")
    public String testProperties() {

        return testProperties.toString();
    }


}
