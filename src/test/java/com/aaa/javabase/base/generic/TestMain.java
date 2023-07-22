package com.aaa.javabase.base.generic;

import com.alibaba.fastjson.JSONObject;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2023/7/22 16:17
 */
public class TestMain {
    public static void main(String[] args) {
        MonitorDataResp<WhiteBoxDataModel> whiteBoxData = new MonitorDataResp<>();
        WhiteBoxDataModel formatResult = new WhiteBoxDataModel();
        formatResult.setName("aa");
        whiteBoxData.setFormatResult(formatResult);
        System.out.println(JSONObject.toJSONString(whiteBoxData));

        MonitorDataResp<StatisticsDataModel> statisticsData = new MonitorDataResp<>();
        StatisticsDataModel formatResult1 = statisticsData.getFormatResult();

    }
}
