package com.aaa.javabase.pattern.behavior.strategy.demo2;

import com.aaa.javabase.pattern.behavior.strategy.demo2.model.enums.ToolTypeEnum;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 ActuatorChooser.java  2024/12/15 14:18
 */
@Component
public class ActuatorChooser {
    @Autowired
    private ObjectProvider<ToolActuator> toolActuators;
    private static Map<ToolTypeEnum, ToolActuator> actuatorMap;

    @PostConstruct
    public void init() {
        actuatorMap = new HashMap<>();
        try {
            // for (ToolActuator toolActuator : toolActuators) {
            //     ChooserActuator annotation = toolActuator.getClass().getAnnotation(ChooserActuator.class);
            //     actuatorMap.put(annotation.value(), toolActuator);
            // }
            toolActuators.stream().peek(e -> {
                ChooserActuator annotation = e.getClass().getAnnotation(ChooserActuator.class);
                actuatorMap.put(annotation.value(), e);
            }).collect(Collectors.toList());

            // Map<ToolTypeEnum, ToolActuator> collect = toolActuators.stream().map(e -> {
            //             Map<ToolTypeEnum, ToolActuator> actuatorMap = new HashMap<>();
            //             ChooserActuator annotation = e.getClass().getAnnotation(ChooserActuator.class);
            //             actuatorMap.put(annotation.value(), e);
            //             return actuatorMap;
            //         }).flatMap(e -> e.entrySet().stream())
            //         .collect(HashMap::new, (o, n) -> o.put(n.getKey(), n.getValue()), HashMap::putAll);
            //         // .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ToolActuator getToolActuator(ToolTypeEnum toolTypeEnum) {
        return actuatorMap.get(toolTypeEnum);
    }
}
