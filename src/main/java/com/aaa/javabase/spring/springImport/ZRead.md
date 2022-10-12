## 情况一 ：声明一个bean
@Import({TestBean1.class})
@Component // @Configuration 也可以
public class AppConfig {
}

public class TestBean1 {
}


## 情况二 ： 实现了ImportSelector接口的类注入Spring容器的方式
