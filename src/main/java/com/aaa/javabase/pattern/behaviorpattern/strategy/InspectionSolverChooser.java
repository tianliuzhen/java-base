package com.aaa.javabase.pattern.behaviorpattern.strategy;

import com.aaa.javabase.pattern.behaviorpattern.strategy.constant.InspectionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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
    public void register() {
        Map<String, InspectionSolver> solverMap  = context.getBeansOfType(InspectionSolver.class);
        for (InspectionSolver value : solverMap .values()) {
            chooseMap.put(value.supports(), value);
             log.info( "任务 {} 处理器 {}注册成功", new Object[]{value.supports(), value});
        }
    }

    public InspectionSolver choose(InspectionEnum type) {
        return chooseMap.get(type);
    }
}
