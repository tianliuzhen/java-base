package com.aaa.javabase.spring.泛型注入;

import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 FxServiceAImpl.java  2025/2/4 22:23
 */
@Component
public class FxServiceStringImpl implements FxService<String> {
    @Override
    public String getData() {
        return "FxServiceStringImpl";
    }
}
