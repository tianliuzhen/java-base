package com.aaa.javabase.web;

import com.aaa.javabase.config.TestProperties;
import com.aaa.javabase.domain.BaseMain;
import com.aaa.javabase.service.GoodsService;
import com.aaa.javabase.spring.conditionBean.service.People;
import com.aaa.javabase.spring.injection.construction.Abean;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version $ Id: WebTest.java, v 0.1 2020/6/18 11:02 liuzhen.tian Exp $
 */

@Log4j2
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


    /**
     * 测试时间：出参
     */
    @GetMapping("/getPeople")
    public People getPeople() {
        People people = new People();
        // Date 类型
        people.setDate(new Date());
        // LocalDateTime 类型
        people.setLocalDateTime(LocalDateTime.now());
        return people;

    }

    /**
     * 入参：
     * {
     * "date": "2022-02-14 20:46:12",
     * "localDateTime": "2022-02-14 20:46:12"
     * }
     * 测试时间：入参
     */
    @PostMapping("/getPeople2")
    public People getPeople2(@RequestBody People people) {
        return people;

    }

    @PostMapping("/getPeople3")
    public Object getPeople3(@RequestParam(defaultValue = "2022-12-14 21:08:00")
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                     Date date) {
        return date;

    }

    @GetMapping("/testBean")
    public void testBean() {
        abean.get();
    }

    @Autowired
    private TestProperties testProperties;

    @PostMapping("/testProperties")
    public String testProperties() {
        return testProperties.toString();
    }


    private static Logger com_dal = LogManager.getLogger("com-dal");
    private static Logger com_util = LogManager.getLogger("com-util");
    @PostMapping("/testLogPrint")
    public void testLogPrint() {
        log.info("###info");
        log.error("###error");
        log.warn("###warn");
        com_dal.info("###com_dal  info");
        com_dal.error("###com_dal  info");
        com_util.error("###com_util  error");
        com_util.info("###com_util  info");

    }

}
