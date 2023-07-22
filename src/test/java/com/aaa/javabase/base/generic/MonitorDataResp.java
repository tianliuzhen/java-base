package com.aaa.javabase.base.generic;

import lombok.Data;

/**
 * @author liuzhen.tian
 * @version 1.0 MonitorDataResp.java  2023/7/22 16:13
 */
@Data
public class MonitorDataResp<E> {
    private Object preData;
    private Object postData;
    private E formatResult;
}
