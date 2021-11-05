package com.aaa.javabase.pattern.behavior.chain.demo3.impl;

import com.aaa.javabase.pattern.behavior.chain.demo3.model.RecruitModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * build offer 状态
 *
 * @author liuzhen.tian
 * @version 1.0 DeleteStatusHandler.java  2021/11/5 21:56
 */

@Component
public class OfferStatusHandler implements RecruitStatusHandler {

    /**
     * 设置招聘列表状态为 ==》offer
     *
     * @param recruitModels model
     */
    @Override
    public void buildStatus(List<RecruitModel> recruitModels) {
        recruitModels.forEach(item -> {
            // TODO: 2021/11/5 业务逻辑处理
            List<String> status = item.getStatus();
            if ("offer".equals(item.getStatusCode())) {
                status.add("offer");
            }
            item.setStatus(status);
        });
    }
}
