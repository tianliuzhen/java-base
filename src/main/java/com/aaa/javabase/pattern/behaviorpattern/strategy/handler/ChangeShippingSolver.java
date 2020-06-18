package com.aaa.javabase.pattern.behaviorpattern.strategy.handler;

import com.aaa.javabase.pattern.behaviorpattern.strategy.InspectionSolver;
import com.aaa.javabase.pattern.behaviorpattern.strategy.constant.InspectionConstant;
import com.aaa.javabase.pattern.behaviorpattern.strategy.constant.InspectionEnum;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version $ Id: ChangeShippingSolver.java, v 0.1 2020/6/18 10:16 liuzhen.tian Exp $
 */
@Component
public class ChangeShippingSolver implements InspectionSolver {
    @Override
    public void slove(Long orderId, Long userId) {
        System.out.println("订单"+orderId+"开始进行转快递了。。");
    }

    @Override
    public InspectionEnum supports() {
        return InspectionEnum.INSPECTION_TASK_TYPE_BATCH_CHANGE_SHIPPING;
    }

}
