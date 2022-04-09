package com.aaa.javabase.pattern.structure.bridging.demo2.messageType;

import com.aaa.javabase.pattern.structure.bridging.demo2.messageImpl.MessageImplementor;
import lombok.extern.slf4j.Slf4j;

/**
 * 特急消息处理
 *
 * @author liuzhen.tian
 * @version 1.0 SpecialUrgentlyMessage.java  2022/4/9 13:36
 */
@Slf4j
public class SpecialUrgentlyMessage extends AbstractMessage {
    public SpecialUrgentlyMessage(MessageImplementor messageImplementor) {
        super(messageImplementor);
    }

    @Override
    public void sendMessage() {
        log.error("【特急】消息处理...");
        super.messageImplementor.send();
    }

    public String getResult() {
        log.error("处理成功");
        return "处理成功";
    }
}
