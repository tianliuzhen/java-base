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
        if(contextRefreshedEvent.getApplicationContext().getParent() != null){
            return;
        }
    }
}
