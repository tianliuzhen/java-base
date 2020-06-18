package com.aaa.javabase.pattern.behaviorpattern.strategy;

import com.aaa.javabase.pattern.behaviorpattern.strategy.constant.InspectionEnum;

/**
 * @author liuzhen.tian
 * @version $ Id: Inspection.java, v 0.1 2020/6/18 9:57 liuzhen.tian Exp $
 */
public interface InspectionSolver {

    void slove(Long orderId, Long userId);

    InspectionEnum supports();

}
