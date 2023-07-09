package com.aaa.javabase.spring.Listener.非注解;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author liuzhen.tian
 * @version 1.0 OrderEvent2.java  2021/5/31 20:37
 */
@Setter
@Getter
public class OrderEvent2 extends ApplicationEvent {
    private String msg;

    public OrderEvent2(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
