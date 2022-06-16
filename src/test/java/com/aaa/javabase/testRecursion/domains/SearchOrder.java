package com.aaa.javabase.testRecursion.domains;

import lombok.Data;

/**
 * @author liuzhen.tian
 * @version 1.0 SearchOrder.java  2022/6/16 20:32
 */

@Data
public class SearchOrder {
    private String searchOrderName;
    private Features features;

    @Data
    public static class Features {
        private String featuresName;
        private SearchOrder searchOrder;
    }

}
