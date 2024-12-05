package com.aaa.javabase.web;

import com.aaa.javabase.base.annotations.testImpl.Boy;
import com.aaa.javabase.base.annotations.testImpl.Human;
import com.aaa.javabase.base.annotations.testImpl.Man;
import com.aaa.javabase.config.PayConfig;
import com.aaa.javabase.config.ProProperties;
import com.aaa.javabase.config.TestProperties;
import com.aaa.javabase.domain.BaseEntity;
import com.aaa.javabase.domain.BaseMain;
import com.aaa.javabase.multithreading.并发执行.completableFuture.CompletableBean;
import com.aaa.javabase.service.GoodsService;
import com.aaa.javabase.spring.conditionBean.service.People;
import com.aaa.javabase.spring.factoryBean.AppleBean;
import com.aaa.javabase.spring.factoryBean.OrangeBean;
import com.aaa.javabase.spring.injection.construction.Abean;
import com.aaa.javabase.spring.springImport.ImportBean1;
import com.aaa.javabase.spring.springImport.ImportBean2;
import com.aaa.javabase.spring.springImport.ImportBean3;
import com.aaa.javabase.util.LogUtil;
import com.aaa.javabase.util.ThreadUtil;
import com.aaa.javabase.util.spring.SpringUtilV1;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version $ Id: WebTest.java, v 0.1 2020/6/18 11:02 liuzhen.tian Exp $
 */

@Log4j2
@RestController
@EnableConfigurationProperties(TestProperties.class)
@CrossOrigin(origins = "*")
public class WebController {


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private Abean abean;

    @Autowired
    private AppleBean appleBean;
    @Autowired
    private OrangeBean orangeBean;


    /**
     * Map 接受 application.properties  map字段
     */
    @Value("#{${wei_xin_config}}")
    private Map<String, String> weiXinConfig;

    public WebController() {
        System.out.println("WebController");
    }

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

    @PutMapping(path = "putTest")
    public Object putTest() {
        return weiXinConfig;
    }
    @PostMapping(path = "postTest")
    public Object postTest(@RequestBody Map map,@RequestParam String name) {
        return map;
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
    public Object objectResponseDate() {
        BaseMain baseMain = new BaseMain();
        baseMain.setDate(new Date());
        baseMain.setLocalDateTime(LocalDateTime.now());

        return baseMain;
    }

    /**
     * 如果没有这里的配置
     * com.aaa.javabase.config.DateConfig#customJackson()
     * 那么前端对 Date和LocalDate的处理，都是比较麻烦的格式。
     *
     * @param baseMain
     * @return
     */
    @PostMapping("/objectRequestDate")
    public Object objectRequestDate(@RequestBody BaseMain baseMain) {
        return JSONObject.toJSONString(baseMain);
    }


    /**
     * 测试时间：出参
     */
    @GetMapping("/getPeople")
    public People getPeople() throws InterruptedException {
        People people = new People();
        people.setName("再试试");
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
    public BaseEntity testBean(@RequestBody BaseEntity va) {
        return new BaseEntity();
    }

    @Autowired
    private TestProperties testProperties;
    @Autowired
    private ProProperties proProperties;

    @Autowired
    private CompletableBean completableBean;

    @Autowired
    private ImportBean1 importBean1;
    @Autowired
    private ImportBean2 importBean2;
    @Autowired
    private ImportBean3 importBean3;

    @Autowired
    private PayConfig payConfig;

    @PostMapping("/testProperties")
    public String testProperties() {
        log.warn("###warn");
        return testProperties.toString();
    }

    // 这里的@Log4j2 注解的log，本质上就是 logger，编译后看到class文件可以看到
    private static final Logger logger = LogManager.getLogger(WebController.class);

    @PostMapping("/testLogPrint")
    public void testLogPrint() {
        /**
         * 记录一个之前理解错的点
         * LogUtil.com_dal 虽然在当前 WebController中调的，但是它的 logger是 com_dal
         * 不是WebController，二者是互不干涉。
         */

        // 指定类去记录日志
        log.info("###log.info WebController");
        log.error("###log.error WebController");
        log.warn("###log.warn WebController");

        // 指定文件去记录日志
        LogUtil.com_dal.info("###LogUtil.com_dal");
        LogUtil.com_dal.error("###LogUtil.com_dal");
    }

    /**
     * 关于注解继承的问题的研究 todo
     */
    @GetMapping("/testAspect")
    public void testAspect() {
        ApplicationContext context = SpringUtilV1.getApplicationContext();

        Human human = context.getBean("human", Human.class);
        System.out.println("---------------------This is a Human.");
        human.say("hello!");
        human.run();

        Human man = context.getBean("man", Man.class);
        System.out.println("---------------------This is a Man.");
        man.say("hello!");
        man.run();

        Boy boy = context.getBean("boy", Boy.class);
        System.out.println("---------------------This is a Boy.");
        boy.say("hello!");
        boy.run();

    }

    @SneakyThrows
    @GetMapping("/executeThread")
    public void executeThread() {
        for (int i = 0; i < 10; i++) {
            ThreadUtil.execute(() -> {
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            });
        }

        TimeUnit.SECONDS.sleep(2);

        ThreadUtil.close();

    }


    @GetMapping(value = "/charsetGbk", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=GBK")
    public void getGbkResponse(HttpServletResponse response) throws IOException {
        String content = "你好，世界！"; // 你的内容
        byte[] gbkBytes = content.getBytes(Charset.forName("GBK")); // 转换为GBK字节

        // 设置响应内容类型和字符集
        response.setContentType("text/plain;charset=GBK");

        // 写入响应体
        response.getOutputStream().write(gbkBytes);
        response.flushBuffer();
    }

    @GetMapping(value = "/charsetDefault")
    public String charsetDefault()  {
        return "你好，世界！";
    }
}

