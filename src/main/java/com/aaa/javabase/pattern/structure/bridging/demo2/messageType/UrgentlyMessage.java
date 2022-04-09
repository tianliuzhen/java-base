package com.aaa.javabase.pattern.structure.bridging.demo2.messageType;

import com.aaa.javabase.pattern.structure.bridging.demo2.messageImpl.MessageImplementor;
import lombok.extern.slf4j.Slf4j;

/**
 * 紧急消息
 *
 * @author liuzhen.tian
 * @version 1.0 UrgentlyMessage.java  2022/4/9 13:31
 */
@Slf4j
public class UrgentlyMessage extends AbstractMessage {
    public UrgentlyMessage(MessageImplementor messageImplementor) {
        super(messageImplementor);
    }

    @Override
    public void sendMessage() {
        log.warn("【加急】消息处理...");
        super.messageImplementor.send();
    }

    /**
     * 紧急消息特有的方法：监听消息
     *
     * @return 消息状态
     */
    public String watch() {
        log.warn("处理中...");
        return "处理中";
    }
}
