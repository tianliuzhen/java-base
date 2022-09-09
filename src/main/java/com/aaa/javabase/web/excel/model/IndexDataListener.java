package com.aaa.javabase.web.excel.model;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuzhen.tian
 * @version 1.0 IndexDataListener.java  2022/9/9 21:59
 */
@Slf4j
public class IndexDataListener implements ReadListener<ImageDemoData> {
    @Override
    public void invoke(ImageDemoData data, AnalysisContext context) {
        log.info("读取到一条数据{}", JSON.toJSONString(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("会在每个sheet读取完毕后调用一次");
    }
}
