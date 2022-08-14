package com.aaa.javabase;

import com.aaa.javabase.service.BeanCycle;
import com.aaa.javabase.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class JavabaseApplicationTests {

    @Autowired
    private BeanCycle myService;

    @Test
    void contextLoads() {
        myService.initMethod();
    }

    @Test
    void TestBeanCycle(){
        myService.initMethod();
        // 使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文。
        // 避免使用application.xml进行配置。相比XML配置，更加便捷。
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanCycle.class);
        System.out.println("IOC容器创建完成........");
        BeanCycle beanCycle = (BeanCycle) ac.getBean("beanCycle");
        beanCycle.initMethod();
        /**
         * close()立即调用doClose()方法进行容器销毁工作；
         * registerShutdownHook()将销毁工作加入虚拟机关闭钩子，随虚拟机关闭时销毁。
         */
        // 仅当关闭 ac 的时候才会调用  DisposableBean.destroy()；控制台才会输出
        // ac.close();
        ac.registerShutdownHook();
    }
    // @Test
    public void changeBean(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavabaseApplication.class);
        UserService bean = context.getBean(UserService.class);
        System.out.println("bean = " + bean);


    }

}
