package com.aaa.javabase.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * Bean 的生命周期
 *
 * 正所谓，天对地，雨对风；
 *            @PostConstruct 对 @PreDestroy；
 *            InitializingBean.afterPropertiesSet() 对 DisposableBean.destroy()；
 *            init-method 对 destroy-method；
 *            雷隐隐，雾蒙蒙；山花对海树，赤日对苍穹；平仄平仄平平仄，仄平仄平仄仄平，仄仄平……
 *
 *
 * 如果设置了 BeanPostProcessor ，则是
 *           1. 对象beanCycle开始实例化（postProcessBeforeInitialization）
 *           2. @PostConstruct 注解使用的方法
 *           3. 实现 InitializingBean  的初始化方法
 *           4. 对象beanCycle实例化完成 （postProcessAfterInitialization）
 *           5. destroy 销毁
 * @author liuzhen.tian
 * @version $ Id: MyServiceImpl.java, v 0.1 2020/7/6 15:50 liuzhen.tian Exp $
 */
@Service
@Slf4j
public class BeanCycle implements InitializingBean, DisposableBean {

    @PostConstruct
    public void PostConstruct() {
        System.out.println("@PostConstruct 注解使用的方法");
    }

    /***
     * 继承了InitializingBean接口，需要实现afterPropertiesSet方法---对应于InitializingBean的用法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("实现 InitializingBean  的初始化方法");
    }

    /***
     * 继承了DisposableBean接口，需要实现destroy方法---对应于DisposableBean的用法
     * 如果想 destroy()生效，要设置   ac.close();
     */
    @Override
    public void destroy() throws Exception {
        log.error("实现 destroy  的销毁方法");
        System.err.println("实现 destroy  的销毁方法");
    }


    /**
     * 使用  @PreDestroy  可以直接输出
     */
    @PreDestroy
    public void preDestroy(){
        System.out.println("@preDestroy 注解使用的方法");
    }

    public void initMethod() {
        log.info("正常的方法");
    }
}
