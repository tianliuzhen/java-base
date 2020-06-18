package com.aaa.javabase.web;

import com.aaa.javabase.pattern.behavior.strategy.InspectionSolver;
import com.aaa.javabase.pattern.behavior.strategy.InspectionSolverChooser;
import com.aaa.javabase.pattern.behavior.strategy.constant.InspectionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhen.tian
 * @version $ Id: WebTest.java, v 0.1 2020/6/18 11:02 liuzhen.tian Exp $
 */
@RestController
public class WebTest {
    @Autowired
    private InspectionSolverChooser inspectionSolverChooser;

    @GetMapping(value = "/inspectionSolver")
    public void test(){
        //准备数据
        InspectionEnum taskType = InspectionEnum.INSPECTION_TASK_TYPE_BATCH_REPLACE_ORDER_GOODS;
        Long orderId = 123L;
        Long userId = 1L;
        // 获取任务类型对应的 solver
        InspectionSolver solver = inspectionSolverChooser.choose(taskType);
        if (solver==null) {
            throw new RuntimeException("任务类型无法处理");
        }
        // 调用不同的solve的方法进行处理
        solver.slove(orderId,userId);


    }
}
