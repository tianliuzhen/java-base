
参考：https://cloud.tencent.com/developer/article/1815457

一、七种方式教你在SpringBoot初始化时搞点事情

    容器刷新完成扩展点

        1、监听容器刷新完成扩展点ApplicationListener<ContextRefreshedEvent>

        2、SpringBoot的CommandLineRunner接口

        3、SpringBoot的ApplicationRunner接口

    Bean初始化完成扩展点
        1、@PostConstruct注解
        2、InitializingBean接口
        3、@Bean注解的初始化方法
        4、通过构造函数注入

二、Application Context获取的几种方式
    一、直接注入 或者是构造注入
        如：@Resource private ApplicationContext ctx;
    二、实现ApplicationContextAware接口（推荐）
        如：public class InspectionSolverChooser implements ApplicationContextAware
    三、实现ApplicationListener<ContextRefreshedEvent>
        如：public class MyContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent>

注：
    Bean初始化完成扩展点执行顺序？
        Constructor ===》 PostConstruct ===》 InitializingBean  ===》 init-method
