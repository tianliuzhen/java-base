package com.aaa.javabase.web.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @author liuzhen.tian
 * @version 1.0 SseEmitterController2.java  2024/8/5 22:27
 */
@Slf4j
@RestController
@RequestMapping("/sse2")
public class SseEmitterController2 {
    @GetMapping("/stream")
    public SseEmitter stream() {
        SseEmitterUtil.getConn(null);

        // 30S超时
        SseEmitter emitter = new SseEmitter(1000L * 30);

        // 注册回调函数，处理服务器向客户端推送的消息
        emitter.onCompletion(() -> {
            System.out.println("Connection completed");
            // 在连接完成时执行一些清理工作
        });

        emitter.onTimeout(() -> {
            System.out.println("Connection timeout");
            // 在连接超时时执行一些处理
            emitter.complete();
        });

        // 在后台线程中模拟实时数据
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    emitter.send(SseEmitter.event().name("message").data("[" + new Date() + "] Data #" + i));
                    Thread.sleep(1000);
                }
                emitter.complete(); // 数据发送完成后，关闭连接
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e); // 发生错误时，关闭连接并报错
            }
        }).start();

        return emitter;
    }


    @GetMapping(value = "getConnAndSend", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter getConnAndSend(@RequestParam(defaultValue = "b") String clientId) {
        final SseEmitter emitter = SseEmitterUtil.getConn(clientId);
        CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(100);
                    SseEmitterUtil.send(clientId);
                }

            } catch (Exception e) {
                throw new RuntimeException("推送数据异常");
            } finally {
                SseEmitterUtil.closeConn(clientId);
            }
        });
        return emitter;
    }


}
