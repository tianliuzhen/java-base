package com.aaa.javabase.pattern.behavior.chain.demo3.impl;

import com.aaa.javabase.pattern.behavior.chain.demo3.RecruitStatusChain;
import com.aaa.javabase.pattern.behavior.chain.demo3.model.RecruitModel;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * build 删除 状态
 *
 * @author liuzhen.tian
 * @version 1.0 DeleteStatusHandler.java  2021/11/5 21:56
 */

@Order(1000)
@Component
public class DeleteStatusHandler implements RecruitStatusHandler {


    /**
     * 设置招聘列表状态为 ==》删除
     *
     * @param recruitModels model
     */
    @Override
    public void buildStatus(List<RecruitModel> recruitModels, RecruitStatusChain recruitStatusChain) {
        recruitModels.forEach(item -> {
            // TODO: 2021/11/5 业务逻辑处理
            List<String> status = item.getStatus();
            if ("delete".equals(item.getStatusCode())) {
                status.add("删除");
            }
            item.setStatus(status);
        });

        // 执行下一个行为链
        recruitStatusChain.handleBuildStatus(recruitModels);
    }
}
