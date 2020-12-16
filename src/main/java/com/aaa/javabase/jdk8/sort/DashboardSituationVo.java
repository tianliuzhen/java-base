package com.aaa.javabase.jdk8.sort;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author jts
 * @date 2020-02-08 22:23
 */
@Data
@Accessors(chain = true)
public class DashboardSituationVo implements Serializable {

    private static final long serialVersionUID = 806846340958525800L;

    private BigDecimal perdayTurnover;

    private BigDecimal perdayTurnoverDod;

    private BigDecimal gmv;


    private BigDecimal gmvDod;

    private BigDecimal recent7Turnover;

    private BigDecimal recent7TurnoverWow;

    private BigDecimal recent7PerdayTurnover;

    private BigDecimal recent7PerdayTurnoverWow;

    private Integer orders;

    private BigDecimal ordersDod;

    private Integer uv;


    private BigDecimal uvDod;

    private Integer recent7Uv;

    private BigDecimal recent7UvTurnoverWow;

    private BigDecimal aov;


    private BigDecimal aovDod;

    private BigDecimal orderConversionRate;


    private BigDecimal orderConversionRateDod;

    List<String> countryList;

    String storeManagerName;


}
