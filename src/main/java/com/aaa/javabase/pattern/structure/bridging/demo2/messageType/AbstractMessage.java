package com.aaa.javabase.pattern.structure.bridging.demo2.messageType;

import com.aaa.javabase.pattern.structure.bridging.demo2.messageImpl.MessageImplementor;

/**
 * @author liuzhen.tian
 * @version 1.0 AbstractMessage.java  2022/4/9 13:05
 */
public abstract class AbstractMessage {

    protected MessageImplementor messageImplementor;

    public AbstractMessage(MessageImplementor messageImplementor) {
        this.messageImplementor = messageImplementor;
    }

    /**
     * 我理解这里可以认为是适配器接口，用于中转处理消息
     */
    public abstract void sendMessage();
}
