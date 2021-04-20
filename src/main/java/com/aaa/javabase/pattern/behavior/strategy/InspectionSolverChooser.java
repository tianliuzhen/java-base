package com.aaa.javabase.pattern.behavior.strategy;

import com.aaa.javabase.pattern.behavior.strategy.constant.InspectionEnum;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class InspectionSolverChooser implements ApplicationContextAware {

    private ApplicationContext context;
    private HashMap<InspectionEnum, InspectionSolver> chooseMap = new HashMap<>(16);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @PostConstruct
    private void register() {
        registerChooser(chooseMap, InspectionSolver.class, InspectionSolver::supports);
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
        Map<String, V> solverMap = context.getBeansOfType(solverClass);
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
