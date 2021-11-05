package com.aaa.javabase.pattern.behavior.chain.demo3;

import com.aaa.javabase.pattern.MyBeanFactory;
import com.aaa.javabase.pattern.behavior.chain.demo3.impl.RecruitStatusHandler;
import com.aaa.javabase.pattern.behavior.chain.demo3.model.RecruitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 不纯责任链模式 demo
 * （每个链的子实现不做否抉择是否执行下一个链）
 * （每个链的子实现类可能都会参与执行）
 * <p>
 * 招聘列表，状态按钮显示链
 *
 * @author liuzhen.tian
 * @version 1.0 RecruitStatusChain.java  2021/11/5 21:54
 */

@Component
public class RecruitStatusChain {

    /**
     * 招聘状态处理器
     */
    private List<RecruitStatusHandler> recruitStatusHandlers;


    @Autowired
    private MyBeanFactory<Void, RecruitStatusHandler> myBeanFactory;

    /**
     * 初始化处理器数据
     */
    @PostConstruct
    public void initData() {
        recruitStatusHandlers = myBeanFactory.getBeansOfType(RecruitStatusHandler.class);
    }

    /**
     * 原始的 List<RecruitModel> ，没有 status
     * 开始执行build招聘里面的每一个状态
     *
     * @param recruitModels
     */
    public void handleBuildStatus(List<RecruitModel> recruitModels) {
        // 执行所有的 招聘状态链处理器
        for (RecruitStatusHandler handler : recruitStatusHandlers) {
            handler.buildStatus(recruitModels);
        }
    }
}
