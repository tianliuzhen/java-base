package com.aaa.javabase.pattern.behavior.observer;

import lombok.Getter;
import lombok.Setter;

import java.util.EventObject;

/**
 * 事件对象
 *
 * @author liuzhen.tian
 * @version 1.0 SwitchEvent.java  2021/8/22 18:17
 */
public class SwitchEvent extends EventObject {

    @Getter
    @Setter
    private String switchState;

    public SwitchEvent(Object source, String switchState) {
        super(source);
        this.switchState = switchState;
    }

}
