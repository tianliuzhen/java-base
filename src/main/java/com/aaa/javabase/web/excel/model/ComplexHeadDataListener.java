package com.aaa.javabase.web.excel.model;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuzhen.tian
 * @version 1.0 ComplexHeadDataListener.java  2022/9/9 21:43
 */
@Slf4j
public class ComplexHeadDataListener implements ReadListener<ComplexHeadData> {
    @Override
    public void invoke(ComplexHeadData data, AnalysisContext context) {
        log.info("读取到一条数据{}", JSON.toJSONString(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("会在每个sheet读取完毕后调用一次");
    }
}
