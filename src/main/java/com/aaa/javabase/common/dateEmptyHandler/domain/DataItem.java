package com.aaa.javabase.common.dateEmptyHandler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/5/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataItem {

    private Long id;

    private Date timeKey;

    private Long total;

    private Long successCount;

    private Long failCount;

}
