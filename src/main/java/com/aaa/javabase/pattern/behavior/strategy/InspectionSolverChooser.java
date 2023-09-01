package com.aaa.javabase.pattern.behavior.strategy;

import com.aaa.javabase.pattern.behavior.strategy.annotion.ChooserName;
import com.aaa.javabase.pattern.behavior.strategy.constant.InspectionEnum;
import com.aaa.javabase.util.BeanContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author liuzhen.tian
 * @version $ Id: InspectionSolverChooser.java, v 0.1 2020/6/18 9:58 liuzhen.tian Exp $
 */
@Slf4j
@Component
@Order(1000000)
// @DependsOn("beanContextUtil")
public class InspectionSolverChooser implements ApplicationContextAware {

    private ApplicationContext context;
    private HashMap<InspectionEnum, InspectionSolver> chooseMap = new HashMap<>(16);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @PostConstruct
    private void register() {
        // 注册商品任务选择器
        // 方式一、 通过 supports方法注册
        // registerChooser(chooseMap, InspectionSolver.class, InspectionSolver::supports);
        // 方式二、 通过 注解 ChooserName 注册
        registerChooser(chooseMap, InspectionSolver.class,
                value -> value.getClass().getAnnotation(ChooserName.class).value());

        // ... 注册其他任务选择器
    }

    /**
     * 注册
     *
     * @param chooseMap   缓存 map
     * @param solverClass 解析器
     * @param getKeyFun   获取key函数
     * @param <K>         key
     * @param <V>         value
     */
    public <K, V> void registerChooser(Map<K, V> chooseMap, Class<V> solverClass, Function<V, K> getKeyFun) {
        Map<String, V> solverMap = BeanContextUtil.getApplicationContext().getBeansOfType(solverClass);
        for (V value : solverMap.values()) {
            K key = getKeyFun.apply(value);
            chooseMap.put(key, value);
            log.info("任务 {} 处理器 {}注册成功", new Object[]{key, value});
        }
    }


    public InspectionSolver choose(InspectionEnum type) {
        return chooseMap.get(type);
    }
}
