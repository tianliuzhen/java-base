package com.aaa.javabase.web;

import com.aaa.javabase.pattern.behavior.chain.demo3.RecruitStatusChain;
import com.aaa.javabase.pattern.behavior.chain.demo3.model.RecruitModel;
import com.aaa.javabase.pattern.behavior.state.demo2.OrderState;
import com.aaa.javabase.pattern.behavior.state.demo2.OrderStateContext;
import com.aaa.javabase.pattern.behavior.state.demo2.OrderStateMap;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.RequireStateMachine;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireActionEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.RequireStateEnum;
import com.aaa.javabase.pattern.behavior.stateMachine.demo1.model.enums.TechTypeEnum;
import com.aaa.javabase.pattern.behavior.strategy.InspectionSolver;
import com.aaa.javabase.pattern.behavior.strategy.InspectionSolverChooser;
import com.aaa.javabase.pattern.behavior.strategy.constant.InspectionEnum;
import com.aaa.javabase.spring.serviceLocatorFactoryBean.RiskParser;
import com.aaa.javabase.spring.serviceLocatorFactoryBean.RiskParserFactory;
import com.aaa.javabase.spring.serviceLocatorFactoryBean.model.RiskParserEnum;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 测试设计模式 controller
 *
 * @author liuzhen.tian
 * @version 1.0 WebPattern.java  2021/11/5 22:34
 */
@Order(value = -12222)
@RequestMapping(value = "pattern/")
@RestController
public class WebPatternController {

    /**
     * 策略
     */
    @Autowired
    private InspectionSolverChooser inspectionSolverChooser;
    /**
     * 策略 (serviceLocatorFactoryBean)
     */
    @Autowired
    private RiskParserFactory riskParserFactory;

    /**
     * 状态
     */
    @Autowired
    private OrderStateMap orderStateMap;

    /**
     * 责任链
     */
    @Autowired
    private RecruitStatusChain recruitStatusChain;

    /**
     * 状态机
     */
    @Autowired
    private RequireStateMachine requireStateMachine;


    /**
     * 状态模式
     */
    @GetMapping(value = "/recruitStatusChain")
    public void recruitStatusChain() {
        // mock 查询列表
        ArrayList<RecruitModel> recruitModels = Lists.newArrayList(
                new RecruitModel("李白", "delete", Lists.newArrayList()),
                new RecruitModel("韩信", "offer", Lists.newArrayList()),
                new RecruitModel("赵云", "recommend", Lists.newArrayList()));

        recruitStatusChain.handleBuildStatus(recruitModels);

        recruitModels.forEach(e -> System.out.println(e.getStatus()));

    }


    /**
     * 状态模式
     */
    @GetMapping(value = "/orderState")
    public void orderState() {
        // 1、获取具体的订单状态对象
        OrderState orderState = orderStateMap.get("shipped-order");

        // 2、使用上下文切换不同的形态
        OrderStateContext orderStateContext = new OrderStateContext();
        orderStateContext.setOrderState(orderState);

        // 3、执行具体的方法
        orderStateContext.doAction("123456");

    }

    /**
     * 策略工厂模式（基于ServiceLocatorFactoryBean实现）
     */
    @GetMapping(value = "/riskParser")
    public void riskParser() {
        RiskParser parser = riskParserFactory.getParser(RiskParserEnum.codingRiskParser);
        parser.scan();

        RiskParser parserw = riskParserFactory.getParser(RiskParserEnum.msgRiskParser);
        parserw.scan();
    }

    /**
     * 策略模式
     */
    @GetMapping(value = "/inspectionSolver")
    public void inspectionSolver() {
        //准备数据
        InspectionEnum taskType = InspectionEnum.INSPECTION_TASK_TYPE_BATCH_REPLACE_ORDER_GOODS;
        Long orderId = 123L;
        Long userId = 1L;
        // 获取任务类型对应的 solver
        InspectionSolver solver = inspectionSolverChooser.choose(taskType);
        if (solver == null) {
            throw new RuntimeException("任务类型无法处理");
        }
        // 调用不同的solve的方法进行处理
        solver.slove(orderId, userId);
    }


    /** trace com.aaa.javabase.web* * -m 2
     * 状态机
     */
    @GetMapping(value = "/requireStateMachine")
    public void requireStateMachine() {


        // 执行动作 (技术创建)
        requireStateMachine.setState(RequireStateEnum.INIT);
        requireStateMachine.execute(TechTypeEnum.TECH, RequireActionEnum.CREATE, "123", "");

        // 执行动作 (技术确认)
        requireStateMachine.setState(RequireStateEnum.NO_CONFIRMED);
        requireStateMachine.execute(TechTypeEnum.TECH, RequireActionEnum.CONFIRM, "123", "");

        // 执行动作 (非技术确认)
        requireStateMachine.setState(RequireStateEnum.NO_CONFIRMED);
        requireStateMachine.execute(TechTypeEnum.NON_TECH, RequireActionEnum.CONFIRM, "123", "");

    }

}
