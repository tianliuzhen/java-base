package com.aaa.javabase.pattern.behavior.strategy.demo1;

import com.aaa.javabase.pattern.behavior.strategy.demo1.constant.InspectionEnum;

/**
 * @author liuzhen.tian
 * @version $ Id: Inspection.java, v 0.1 2020/6/18 9:57 liuzhen.tian Exp $
 */
public interface InspectionSolver {

    void slove(Long orderId, Long userId);

    InspectionEnum supports();

}
