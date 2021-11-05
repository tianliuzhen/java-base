package com.aaa.javabase.pattern.behavior.chain.demo3.impl;

import com.aaa.javabase.pattern.behavior.chain.demo3.model.RecruitModel;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * build 推荐 状态
 *
 * @author liuzhen.tian
 * @version 1.0 DeleteStatusHandler.java  2021/11/5 21:56
 */

@Order(998)
@Component
public class RecommendStatusHandler implements RecruitStatusHandler {

    /**
     * 设置招聘列表状态为 ==》推荐
     *
     * @param recruitModels model
     */
    @Override
    public void buildStatus(List<RecruitModel> recruitModels) {
        recruitModels.forEach(item -> {
            // TODO: 2021/11/5 业务逻辑处理
            List<String> status = item.getStatus();
            if ("recommend".equals(item.getStatusCode())) {
                status.add("recommend");
            }
            item.setStatus(status);
        });
    }
}
