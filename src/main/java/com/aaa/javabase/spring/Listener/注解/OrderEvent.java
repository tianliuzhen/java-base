package com.aaa.javabase.spring.Listener.注解;

import lombok.Builder;
import lombok.Getter;

/**
 * 事件源
 *
 * @author liuzhen.tian
 * @version 1.0 OrderEvent.java  2021/5/31 20:33
 */
@Getter
@Builder(toBuilder = true)
public class OrderEvent {
    private String msg;

    public static void main(String[] args) {
        boolean[] bs = {};

    }
}
