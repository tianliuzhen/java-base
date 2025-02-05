package com.aaa.javabase.spring.injection.泛型注入;

import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 FxServiceAImpl.java  2025/2/4 22:23
 */
@Component
public class FxServiceNumberImpl implements FxService<Number> {
    public FxServiceNumberImpl() {
        System.out.println();
    }

    @Override
    public Number getData() {
        return 1L;
    }
}
