package com.aaa.javabase.spring.Listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 参考：https://www.cnblogs.com/doudouxiaoye/p/5962390.html
 * Spring通过ApplicationListener接口来触发contextrefreshedevent事件
 * 在开发时有时候需要在整个应用开始运行时执行一些特定代码，比如初始化环境，准备测试数据、加载一些数据到内存等等。
 * 在Spring中可以通过ApplicationListener来实现相关的功能，加载完成后触发contextrefreshedevent事件（上下文件刷新事件）
 *
 * @author liuzhen.tian
 * @version 1.0 MyContextRefreshedEvent.java  2021/5/31 20:58
 */
@Component
public class MyContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("我的父容器为：" + contextRefreshedEvent.getApplicationContext().getParent());
        System.out.println("初始化时我被调用了。");

        /**
         * 此处使用Spring boot来进行操作，没有出现二次调用的问题。
         * 在使用传统的application.xml和project-servlet.xml配置中会出现二次调用的问题。
         * 主要原因是初始化root容器之后，会初始化project-servlet.xml对应的子容器。
         * 我们需要的是只执行一遍即可。那么上面打印父容器的代码用来进行判断排除子容器即可
         */
        // root application context 没有parent，他就是老大.
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            // 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
        }
    }
}
