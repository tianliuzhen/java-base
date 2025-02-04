package com.aaa.javabase.spring.泛型注入;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 FxFactory.java  2025/2/4 22:28
 */
@Component
public class FxFactory {
    @Autowired
    private FxService<Number> fxService1;

    @Autowired
    private FxService<String> fxService2;

    // public FxFactory(FxService<Number> fxService) {
    //     this.fxService = fxService;
    // }

}
