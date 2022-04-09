package com.aaa.javabase.pattern.structure.bridging.demo2.messageType;

import com.aaa.javabase.pattern.structure.bridging.demo2.messageImpl.MessageImplementor;
import lombok.extern.slf4j.Slf4j;

/**
 * 普通消息
 *
 * @author liuzhen.tian
 * @version 1.0 CommonMessage.java  2022/4/9 13:25
 */

@Slf4j
public class CommonMessage extends AbstractMessage {
    public CommonMessage(MessageImplementor messageImplementor) {
        super(messageImplementor);
    }

    @Override
    public void sendMessage() {
        log.info("普通消息处理...");
        super.messageImplementor.send();
    }
}
