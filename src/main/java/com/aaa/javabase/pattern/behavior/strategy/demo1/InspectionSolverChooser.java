package com.aaa.javabase.pattern.behavior.strategy.demo1;

import com.aaa.javabase.pattern.behavior.strategy.demo1.annotion.ChooserName;
import com.aaa.javabase.pattern.behavior.strategy.demo1.constant.InspectionEnum;
import com.aaa.javabase.util.spring.SpringUtilV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
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
@DependsOn("springUtilV1")
public class InspectionSolverChooser implements ApplicationContextAware {

    private ApplicationContext context;
    private Map<InspectionEnum, InspectionSolver> chooseMap = new HashMap<>(16);


    @Autowired
    // private List<InspectionSolver> inspectionSolvers;
    // private InspectionSolver[] inspectionSolvers;
    private ObjectProvider<InspectionSolver> inspectionSolvers;


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

        // 方式三、借助于ObjectProvider
        // chooseMap = inspectionSolvers.stream().collect(Collectors.toMap(InspectionSolver::supports, Function.identity()));


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
        Map<String, V> solverMap = SpringUtilV1.getApplicationContext().getBeansOfType(solverClass);
        for (V value : solverMap.values()) {
            K key = getKeyFun.apply(value);
            chooseMap.put(key, value);
            log.info("任务 {} 处理器 {}注册成功", new Object[]{key, value});
        }
    }


    /**
     * trace com.aaa.javabase.pattern.behavior.strategy.demo1.InspectionSolverChooser choose
     */
    public InspectionSolver choose(InspectionEnum type) {
        return chooseMap.get(type);
    }
}
