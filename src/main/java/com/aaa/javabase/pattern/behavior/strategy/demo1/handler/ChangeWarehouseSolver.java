package com.aaa.javabase.pattern.behavior.strategy.demo1.handler;

import com.aaa.javabase.pattern.behavior.strategy.demo1.InspectionSolver;
import com.aaa.javabase.pattern.behavior.strategy.demo1.annotion.ChooserName;
import com.aaa.javabase.pattern.behavior.strategy.demo1.constant.InspectionEnum;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version $ Id: ChangeWarehouseSolver.java, v 0.1 2020/6/18 10:13 liuzhen.tian Exp $
 */
@Component
@ChooserName(value = InspectionEnum.INSPECTION_TASK_TYPE_BATCH_CHANGE_WAREHOUSE)
public class ChangeWarehouseSolver implements InspectionSolver {
    @Override
    public void slove(Long orderId, Long userId) {
        System.out.println("订单"+orderId+"开始进行批量转仓了。。");
    }

    @Override
    public InspectionEnum supports() {
        return InspectionEnum.INSPECTION_TASK_TYPE_BATCH_CHANGE_WAREHOUSE;
    }


}
