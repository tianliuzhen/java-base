package com.aaa.javabase.web;

import com.aaa.javabase.spring.conditionBean.service.People;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 GcTestController.java  2023/10/7 21:59
 */

@Slf4j
@RestController
@RequestMapping(value = "/gcTest")
public class GcTestController {


    private int _1M = 1024 * 1024;
    private List<byte[]> bytes = new ArrayList<>();

    @GetMapping(value = "/addEden")
    public void addEden() {
        if (bytes == null) {
            bytes = new ArrayList<>();
        }
        bytes.add(new byte[100 * _1M]);
    }

    @SneakyThrows
    @GetMapping(value = "/addEdenBatch")
    public void addEdenBatch(@RequestParam(defaultValue = "5") int count) {
        if (bytes == null) {
            bytes = new ArrayList<>();
        }
        for (int i = 0; i < count; i++) {
            TimeUnit.MILLISECONDS.sleep(200);
            bytes.add(new byte[50 * _1M]);
        }
    }

    @SneakyThrows
    @GetMapping(value = "/addAloneByte")
    public void addAloneByte() {
        for (int i = 0; i < 20; i++) {
            TimeUnit.MILLISECONDS.sleep(200);
            byte[] bytes1 = new byte[50 * _1M];
        }
    }


    @GetMapping(value = "/clear")
    public void clear() {
        bytes.clear(); // 因为对象的根本地址不变，gc超过15次后，会直接进入old，不再进入Eden
        // bytes = null;
    }

    @GetMapping(value = "/doGc")
    public void doGc() {
        System.gc();
    }

    @RequestMapping("/metaSpace0om")
    public void metaSpaceOom() {
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(People.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                System.out.println("我是加强类·输出print之前的加强方法");
                return methodProxy.invokeSuper(o, objects);
            });
            People people = (People) enhancer.create();
            people.print();
            System.out.println(people.getClass());
            // 返回自Java虚拟机启动以来加载的类的总数
            System.out.println("totalClass:" + classLoadingMXBean.getTotalLoadedClassCount());
            // 返回自Java虚拟机已加载的类的数量
            System.out.println("activeClass:" + classLoadingMXBean.getLoadedClassCount());
            // 返回自Java虚拟机启动以来卸载载的类的总数
            System.out.println("unloadedClass:" + classLoadingMXBean.getUnloadedClassCount());
        }
    }
}
